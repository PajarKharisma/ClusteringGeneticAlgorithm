package gui;

import algorithm.GeneticAlgorithm;
import geneticElement.Chromosome;
import geneticElement.ColorValues;
import geneticProcess.FitnessValueCalculate;
import geneticProcess.GenerateChromosome;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.RandomProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ControlPanel extends JPanel implements ActionListener {

    private JButton btnProcess, btnSave, btnLoad;
    private DrawPanel drawPanel;
    private JFileChooser jf = new JFileChooser("kordinat");
    private JTextField txtCluster;
    private JComboBox cbRepresentation, cbCrossover, cbSelection, cbMutation;
    private JCheckBox checkPrint;
    private JLabel lblPrint;

    private RandomProcess randomProcess;

    public ControlPanel(){
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        flowLayout.setVgap(20);
        flowLayout.setHgap(30);
        this.setLayout(flowLayout);

        this.setPreferredSize(new Dimension(600,120));

        txtCluster = new JTextField();
        txtCluster.setColumns(5);
        this.add(txtCluster);

        cbRepresentation = new JComboBox();
        cbRepresentation.addItem("Locus");
        cbRepresentation.addItem("Variable");
        this.add(cbRepresentation);

        cbSelection = new JComboBox();
        cbSelection.addItem("Best");
        cbSelection.addItem("Random");
        this.add(cbSelection);

        cbCrossover = new JComboBox();
        cbCrossover.addItem("One Point");
        cbCrossover.addItem("Uniform");
        this.add(cbCrossover);

        cbMutation = new JComboBox();
        cbMutation.addItem("Replacement");
        cbMutation.addItem("Swap");
        cbMutation.addItem("Replacement - Swap");
        this.add(cbMutation);

        btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        this.add(btnSave);

        btnLoad = new JButton("Load");
        btnLoad.addActionListener(this);
        this.add(btnLoad);

        btnProcess = new JButton("Process");
        btnProcess.addActionListener(this);
        this.add(btnProcess);

        checkPrint = new JCheckBox();
        this.add(checkPrint);

        lblPrint = new JLabel("Print log");
        this.add(lblPrint);
    }

    public void setOtherPanel(DrawPanel drawPanel){
        this.drawPanel = drawPanel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object ob = actionEvent.getSource();

        if(ob.equals(btnSave)){
            int choose = jf.showSaveDialog(this);
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jf.setDialogTitle("Pilih lokasi dan nama file");

            if (choose == JFileChooser.APPROVE_OPTION) {
                String path = jf.getSelectedFile().getAbsolutePath();
                String fileName = FilenameUtils.removeExtension(path);
                JSONArray posList = new JSONArray();
                for (Point p:drawPanel.getPoints()) {
                    JSONObject pos = new JSONObject();
                    pos.put("x", (int)p.getX());
                    pos.put("y", (int)p.getY());
                    posList.add(pos);
                }

                try (FileWriter file = new FileWriter(fileName+".zap")) {
                    file.write(posList.toJSONString());
                    file.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Kordinat telah disimpan");
            }
        }

        if(ob.equals(btnLoad)){
            int choose = jf.showOpenDialog(this);
            if (choose == JFileChooser.APPROVE_OPTION) {
                String path = jf.getSelectedFile().getAbsolutePath();
                JSONParser jsonParser = new JSONParser();
                ArrayList<Point> points = new ArrayList<>();

                try (FileReader reader = new FileReader(path)){
                    JSONArray obj = (JSONArray) jsonParser.parse(reader);
                    for (Object o:obj) {
                        JSONObject pos = (JSONObject)o;
//                        points.add(new Point((int)pos.get("x"), (int)pos.get("y")));
                        int x = Integer.parseInt(pos.get("x").toString());
                        int y = Integer.parseInt(pos.get("y").toString());
                        points.add(new Point(x,y));
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                drawPanel.setPoints(points);
                drawPanel.repaint();
            }
        }

        if(ob.equals(btnProcess)){
            if(txtCluster.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Anda belum menginputkan jumlah cluster");
            } else {
                int selection = cbSelection.getSelectedIndex();
                int crossover = cbCrossover.getSelectedIndex();
                int mutation = cbMutation.getSelectedIndex();
                int representation = cbRepresentation.getSelectedIndex();

                String selectionData = cbSelection.getSelectedItem().toString();
                String crossoverData = cbCrossover.getSelectedItem().toString();
                String mutationData = cbMutation.getSelectedItem().toString();
                String representationData = cbRepresentation.getSelectedItem().toString();

                boolean isPrint = checkPrint.isSelected();

                int clusterAmount = Integer.parseInt(txtCluster.getText());
                RandomProcess randomProcess = new RandomProcess();

                GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
                geneticAlgorithm.setVariable(selection, crossover, mutation, representation);
                geneticAlgorithm.setData(selectionData, crossoverData, mutationData, representationData, isPrint);

                ArrayList<Integer> listOfCluster = geneticAlgorithm.process(drawPanel.getPoints(), clusterAmount);
                ArrayList<ColorValues> listOfColor = randomProcess.getColorValues(clusterAmount);

                drawPanel.setClusterIndex(listOfCluster);
                drawPanel.setColorValues(listOfColor);
                drawPanel.repaint();
            }
        }
    }
}
