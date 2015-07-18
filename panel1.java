import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

class panel1 extends JPanel implements ActionListener, FocusListener
{
    DefaultTableCellRenderer renderer;
    JSplitPane jsp;
    ResultSet k;
    JTable jt;
    JScrollPane js;
    Dimension d,d1;
    JPanel p,q;
    JLabel did,name,dt;
    JTextField tdid,tnm,tdt;
    JButton mod,close,rfrsh;
    String a,b,c;
    modForm m;

    panel1(modForm m1)
    {
        renderer=new DefaultTableCellRenderer();
        m=m1;
        setLayout(null);
        jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jt=new JTable();
        k=null;
        String [][]arr=new String[300][11];
        String []chd={"ID","Name","BloodGr","Date","Age","Weight","Gender","Frequency","Adm. Year","Contact No","E-mail"};
        js=new JScrollPane(jt);
        int i=0;
        try
        {
            k=Delete.disp();
            while(k.next())
            {
                arr[i][0]=""+k.getInt(1);
                arr[i][1]=k.getString(2);
                arr[i][2]=k.getString(3);
                arr[i][3]=""+k.getInt(9)+"/"+k.getInt(10)+"/"+k.getInt(11);
                arr[i][4]=""+k.getInt(4);
                arr[i][5]=""+k.getInt(5);
                arr[i][6]=k.getString(8);
                arr[i][7]=""+k.getInt(13);
                arr[i][8]=""+k.getInt(12);
                arr[i][9]=k.getString(6);
                arr[i][10]=k.getString(7);
                i++;
            }
        }
        catch(Exception ew){}
        DefaultTableModel model=new DefaultTableModel(arr,chd);
        jt.setModel(model);
        setTableCellAlignment(0);
        jt.setAutoCreateColumnsFromModel(false);
        sortAllRowsBy(model,0,true);

        p=new JPanel();
        p.setLayout(null);
        q=new JPanel();
        did=new JLabel("Donor ID :-");
        did.setBounds(226,30,60,30);
        name=new JLabel("Name :-");
        name.setBounds(239,70,60,30);
        dt=new JLabel("Date :-");
        dt.setBounds(245,110,60,30);
        tdid=new JTextField();
        tdid.setBounds(288,35,60,23);
        tdid.addFocusListener(this);
        tdid.setEditable(false);
        tnm=new JTextField();
        tnm.setBounds(288,75,390,23);
        tnm.addFocusListener(this);
        tnm.setEditable(false);
        tdt=new JTextField();
        tdt.setBounds(288,115,150,23);
        tdt.addFocusListener(this);
        tdt.setEditable(false);
        p.add(did);p.add(tdid);
        p.add(name);p.add(tnm);
        p.add(dt);p.add(tdt);
        mod=new JButton("MODIFY ENTRY");
        mod.addActionListener(this);
        mod.setBounds(150,175,220,28);
        close=new JButton("CLOSE");
        close.addActionListener(this);
        close.setBounds(380,175,220,28);
        rfrsh=new JButton("REFRESH");
        rfrsh.addActionListener(this);
        rfrsh.setBounds(610,175,220,28);
        p.add(mod);p.add(close);p.add(rfrsh);

        d=new Dimension(600,500);
        d1=new Dimension(600,200);
        jsp.setTopComponent(js);
        js.setMinimumSize(d);
        p.setMinimumSize(d1);
        jsp.setBottomComponent(p);
        jsp.setBounds(0,0,1000,700);
        add(jsp);
    }

    void setTableCellAlignment(int alignment)
    {
        renderer.setHorizontalAlignment(alignment);
        for(int i=0;i<jt.getColumnCount();i++)
        {
            jt.setDefaultRenderer(jt.getColumnClass(i),renderer);
        }
        // repaint to show table cell changes
        jt.updateUI();
     }

    public void sortAllRowsBy(DefaultTableModel model, int colIndex, boolean ascending)
    {
        Vector data = model.getDataVector();
        Collections.sort(data, new ColumnSorter(colIndex, ascending));
        model.fireTableStructureChanged();
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b=(JButton)e.getSource();
        if(b==mod)
        {
            modify.did=Integer.parseInt(tdid.getText());
            try
            {
                k=modify.disp();
                while(k.next())
                {
                    modify.name=k.getString(2);
                    modify.blg=k.getString(3);
                    modify.age=k.getInt(4);
                    modify.wt=k.getInt(5);
                    modify.cno=k.getString(6);
                    modify.email=k.getString(7);
                    modify.admyr=k.getInt(12);
                    modify.gen=k.getString(8);
                    modify.cnt=k.getInt(13);
                    modify.dt=k.getInt(9);
                    modify.mnth=k.getInt(10);
                    modify.yr=k.getInt(11);
                }
            }
            catch(Exception ew)
            {}
        }
        if(b==close)
        {
            int opn=0;
            String []opt={"NO","YES"};
            while(true)
            {
                opn=JOptionPane.showOptionDialog(null,"Are you sure you want to quit the MODIFY ENTRY FORM?","Confirmation",JOptionPane.NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opt,0);
                if(opn==0)
                    break;
                else
                {
                    m.setVisible(false);
                    break;
                }
            }
        }
        if(b==rfrsh)
        {
            m.setVisible(false);
            modForm mf=new modForm("MODIFY ENTRY");
        }
        tdid.setText("");
        tnm.setText("");
        tdt.setText("");
    }

    public void focusGained(FocusEvent e)
    {
        JTextField t=(JTextField)e.getSource();
        try
        {
            a=""+jt.getModel().getValueAt(jt.getSelectedRow(),0);
            b=""+jt.getModel().getValueAt(jt.getSelectedRow(),1);
            c=""+jt.getModel().getValueAt(jt.getSelectedRow(),3);
        }
        catch(Exception ew)
        {
            a="";
            b="";
            c="";
        }
        if(t==tnm)
        {
            tdid.setText(a);
            tnm.setText(b);
            tdt.setText(c);
        }
        if(t==tdid)
        {
            tdid.setText(a);
            tnm.setText(b);
            tdt.setText(c);
        }
        if(t==tdt)
        {
            tdid.setText(a);
            tnm.setText(b);
            tdt.setText(c);
        }
    }

    public void focusLost(FocusEvent e)
    {}
}