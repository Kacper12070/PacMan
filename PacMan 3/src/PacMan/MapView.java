package PacMan;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MapView extends JPanel {
    private int[][] mapa;
    private JTable table;
    private JLabel scoreLabel = new JLabel("Score: 0");
    public MapView(int rows, int columns) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.mapa = MapGenerator.generateMap(rows, columns);

        TableModel model = new AbstractTableModel(){
            public int getColumnCount() {
                return mapa[0].length;
            }
            public int getRowCount() {
                return mapa.length;
            }
            public Object getValueAt(int row, int col) {
                return mapa[row][col];
            }
        };
        table = new JTable(model);
        table.setShowGrid(true);
        table.setGridColor(Color.gray);
        table.setTableHeader(null);
        table.setEnabled(false);
        table.setIntercellSpacing(new Dimension(1, 1));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {//https://docs.oracle.com/javase/8/docs/api/javax/swing/table/DefaultTableCellRenderer.html
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel();
                cell.setOpaque(true);
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                int val = (int) value;

                if(val==1){
                    cell.setBackground(Color.yellow);
                }else if(val==2){
                    cell.setBackground(Color.black);
                    cell.setText("•");
                    cell.setForeground(Color.GREEN);
                }else if(val==0){
                    cell.setText("");
                    cell.setBackground(Color.black);
                }else if(val==3){
                    cell.setBackground(Color.BLACK);
                    cell.setOpaque(true);
                    cell.setHorizontalAlignment(SwingConstants.CENTER);
                    cell.setVerticalAlignment(SwingConstants.CENTER);
                    ImageIcon icon = new ImageIcon(getClass().getResource("/PacMan/images/pacman.png"));
                    Image scaled = icon.getImage().getScaledInstance(table.getRowHeight(), table.getRowHeight(), Image.SCALE_SMOOTH);
                    cell.setIcon(new ImageIcon(scaled));
                }else if(val==4){
                    cell.setBackground(Color.BLACK);
                    cell.setOpaque(true);
                    cell.setHorizontalAlignment(SwingConstants.CENTER);
                    cell.setVerticalAlignment(SwingConstants.CENTER);
                    ImageIcon icon = new ImageIcon(getClass().getResource("/PacMan/images/ghost.png"));
                    Image scaled = icon.getImage().getScaledInstance(table.getRowHeight(), table.getRowHeight(), Image.SCALE_SMOOTH);
                    cell.setIcon(new ImageIcon(scaled));
                }else if(val==5){//DOUBLE POINTS
                    cell.setBackground(Color.BLACK);
                    cell.setText("•");
                    cell.setForeground(Color.BLUE);
                }else if(val==6) {//TRIPLE POINTS
                    cell.setBackground(Color.BLACK);
                    cell.setText("•");
                    cell.setForeground(Color.ORANGE);
                }else if(val==7) {//SPEED UP
                    cell.setBackground(Color.BLACK);
                    cell.setText("*");
                    cell.setForeground(Color.RED);
                }
                return cell;
            }
        });

        this.add(table, BorderLayout.CENTER);

        //Punktacja
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(Color.BLACK);
        scoreLabel.setOpaque(true);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(scoreLabel, BorderLayout.SOUTH);

        //skroty klawiszowe
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_Q &&
                    e.isControlDown() && e.isShiftDown()){

                    Container parent = getParent();
                    while (parent != null && !(parent instanceof JFrame))
                        parent = parent.getParent();
                    if(parent instanceof JFrame frame){
                        Component content = frame.getContentPane();
                        if(content instanceof JPanel cards){
                            CardLayout layout = (CardLayout) cards.getLayout();
                            layout.show(cards, "menu");
                        }
                    }
                }
            }
        });
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    public int[][] getMap() {
        return mapa;
    }
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
    public void setController(KeyListener controller) {
        this.addKeyListener(controller);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    public void setCellSize(int size) {
        table.setRowHeight(size);
        table.repaint();
    }
}

