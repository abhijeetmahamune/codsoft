import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GradeCalculator implements ActionListener {
    JFrame frame;
    JLabel label1, label2, resultLabel;
    JTextField field1;
    JButton buttonStart;
    JDialog dialog;
    JTextField marksField;
    JLabel dialogLabel;
    JButton dialogSubmit;
    int numSubjects, totalMarks;
    int[] marks;
    int currentSubjectIndex;
    String Grade = "";

    GradeCalculator() {
        frame = new JFrame("Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 1));
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        label1 = new JLabel("Grade Calculator", JLabel.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 22));
        label1.setForeground(new Color(70, 130, 180));

        label2 = new JLabel("Enter number of subjects:", JLabel.CENTER);
        label2.setFont(new Font("Arial", Font.PLAIN, 16));

        field1 = new JTextField(10);
        field1.setFont(new Font("Arial", Font.PLAIN, 16));
        field1.setBackground(new Color(255, 250, 240));
        field1.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2));

        buttonStart = new JButton("Start");
        buttonStart.setFont(new Font("Arial", Font.BOLD, 16));
        buttonStart.setBackground(new Color(173, 216, 230));
        buttonStart.setForeground(Color.BLACK);
        buttonStart.setFocusPainted(false);
        buttonStart.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        buttonStart.addActionListener(this);

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(34, 139, 34));

        frame.add(label1);
        frame.add(label2);
        frame.add(field1);
        frame.add(buttonStart);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == buttonStart) {
            numSubjects = Integer.parseInt(field1.getText());
            marks = new int[numSubjects];
            currentSubjectIndex = 0;
            totalMarks = 0;
            showDialogForMarks();
        }

        if (ae.getSource() == dialogSubmit) {
            marks[currentSubjectIndex] = Integer.parseInt(marksField.getText());
            totalMarks += marks[currentSubjectIndex];
            currentSubjectIndex++;
            dialog.dispose();

            if (currentSubjectIndex < numSubjects) {
                showDialogForMarks();
            } else {
                calculateAndShowResult();
            }
        }
    }

    public void showDialogForMarks() {
        dialog = new JDialog(frame, "Enter Marks", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(frame);
        dialog.getContentPane().setBackground(new Color(240, 255, 255));

        dialogLabel = new JLabel("Enter marks for subject " + (currentSubjectIndex + 1), JLabel.CENTER);
        dialogLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dialogLabel.setForeground(Color.DARK_GRAY);

        marksField = new JTextField(10);
        marksField.setFont(new Font("Arial", Font.PLAIN, 16));
        marksField.setBackground(new Color(255, 250, 240));
        marksField.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 2));

        dialogSubmit = new JButton("Submit");
        dialogSubmit.setFont(new Font("Arial", Font.BOLD, 14));
        dialogSubmit.setBackground(new Color(144, 238, 144));
        dialogSubmit.setForeground(Color.BLACK);
        dialogSubmit.setFocusPainted(false);
        dialogSubmit.addActionListener(this);

        dialog.add(dialogLabel, BorderLayout.NORTH);
        dialog.add(marksField, BorderLayout.CENTER);
        dialog.add(dialogSubmit, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public void calculateAndShowResult() {
        int maxMarks = numSubjects * 100;
        double percentage = (double) totalMarks / maxMarks * 100;
        int percentage1 = (int) Math.round(percentage);
        if (percentage1 <= 100 && percentage1 >= 91) {
            Grade = "O";
        } else if (percentage1 <= 90 && percentage1 >= 81) {
            Grade = "A+";
        } else if (percentage1 <= 80 && percentage1 >= 71) {
            Grade = "A";
        } else if (percentage1 <= 70 && percentage1 >= 61) {
            Grade = "B";
        } else if (percentage1 <= 60 && percentage1 >= 50) {
            Grade = "C";
        } else if (percentage1 < 50) {
            Grade = "F";
        }
        resultLabel.setText("Total Marks: " + totalMarks + " | Percentage: " + String.format("%.2f", percentage)
                + "% | Grade:  " + Grade);

        JOptionPane.showMessageDialog(frame, resultLabel.getText(), "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new GradeCalculator();
    }
}
