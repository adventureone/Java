package Java.com;

/**
 * 模拟科学计算器
 * 要求：界面模拟 Windows中的计算器程序。
 * 实现基本数学运算、函数等功能：加、减、乘、除、阶乘、正弦、余弦和指数运算。
 * 实现要点：添加相关组件并进行按钮事件处理。
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CalculateView extends JFrame implements ActionListener
{
    private String[] KEYS={"7","8","9","*","4","5","6","-","1","2","3","+","0","e","π","÷","c","%",".","=","(",")","sqr","x*x","!","sin","cos","^"};
    private JButton keys[]=new JButton[KEYS.length];
    private JTextField resultText = new JTextField("0.0");   //文本框默认值
    private String b="";
    public CalculateView()
    {
        super("计算器");  //GUI名称
        this.setLayout(null);    //设置窗口布局
        resultText.setBounds(20, 5, 255, 40);  //设置文本框窗口大小
        resultText.setHorizontalAlignment(JTextField.RIGHT);     //让文本显示在文本框得右边
        resultText.setEditable(false);     //让文本框内容不可写入
        this.add(resultText);   //添加文本框
        int x=20,y=55;      //按钮的宽高
        for (int i=0;i<KEYS.length;i++)
        {
            keys[i] = new JButton();
            keys[i].setText(KEYS[i]);
            keys[i].setBounds(x, y, 60, 40);
            if(x<215)
            {
                x+=65;
            }
            else
            {
                x = 20;
                y+=45;
            }
            this.add(keys[i]);
        }
        for (int i = 0; i <KEYS.length; i++)
        {
            keys[i].addActionListener(this);
        }
        this.setResizable(false);
        this.setIconImage((new ImageIcon("calculate.jpg").getImage()));    //窗口图标
        this.setResizable(false);                //窗口图标
        this.setBounds(500, 200, 300, 420);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);       //关闭方法
        this.setVisible(true);                    //显示窗口
    }
    public void actionPerformed(ActionEvent e)
    {
        String label = e.getActionCommand();      //getActionCommand()区分到底是哪个组件被单击了
        if (label=="c"||label=="=")
        {
            if(label=="=")
            {
                String s[]=yunsuan(this.b);
                String result=Result(s);
                this.b=result+"";
                resultText.setText(this.b);       //setText()输出string类型的字符串变量
            }
            else
            {
                this.b="";
                resultText.setText("0");
            }
        }
        else if (label=="sqr")
        {
            String n=yunsuan2(this.b);
            resultText.setText(n);
            this.b=n;
        }
        else if(label=="x*x")
        {
            String m=yunsuan3(this.b);
            resultText.setText(m);
            this.b=m;
        }
        else if(label=="!")
        {
            String m=yunsuan4(this.b);
            resultText.setText(m);
            this.b=m;
        }else if(label=="sin")
        {
            String m=yunsuan5(this.b);
            resultText.setText(m);
            this.b=m;
        }else if(label=="cos")
        {
            String m=yunsuan6(this.b);
            resultText.setText(m);
            this.b=m;
        }
        else if(label=="e"||label=="π")
        {
            if (label=="e")
            {
                String m=String.valueOf(2.71828);
                this.b=this.b+m;
                resultText.setText(this.b);
            }
            if (label=="π")
            {
                String m=String.valueOf(3.14159265);
                this.b=this.b+m;
                resultText.setText(this.b);
            }
        }
        else
        {
            this.b=this.b+label;
            resultText.setText(this.b);
        }
    }
    private String[] yunsuan(String str)
    {
        String s="";
        char a[]=new char[100];
        String jieguo[]=new String[100];
        int top=-1,j=0;
        for (int i=0;i<str.length();i++)
        {
            if ("0123456789.".indexOf(str.charAt(i))>=0)
            {
                s="";
                for (;i<str.length()&&"0123456789.".indexOf(str.charAt(i))>=0;i++)
                {
                    s=s+str.charAt(i);
                }
                i--;
                jieguo[j]=s;
                j++;
            }
            else if ("(".indexOf(str.charAt(i))>=0)
            {
                top++;
                a[top]=str.charAt(i);
            }
            else if (")".indexOf(str.charAt(i))>=0)
            {
                for (;;)
                {
                    if (a[top]!='(')
                    {
                        jieguo[j]=a[top]+"";
                        j++;
                        top--;
                    }
                    else
                    {
                        top--;
                        break;
                    }
                }
            }
            else if ("*%÷^".indexOf(str.charAt(i))>=0)
            {
                if (top==-1)
                {
                    top++;
                    a[top]=str.charAt(i);
                }
                else
                {
                    if ("*%÷^".indexOf(a[top])>=0)
                    {
                        jieguo[j]=a[top]+"";
                        j++;
                        a[top]=str.charAt(i);
                    }
                    else if ("(".indexOf(a[top])>=0)
                    {
                        top++;
                        a[top]=str.charAt(i);
                    }
                    else if ("+-".indexOf(a[top])>=0)
                    {
                        top++;
                        a[top]=str.charAt(i);
                    }
                }
            }
            else if ("+-".indexOf(str.charAt(i))>=0)
            {
                if (top==-1)
                {
                    top++;
                    a[top]=str.charAt(i);
                }
                else
                {
                    if ("%*÷^".indexOf(a[top])>=0)
                    {
                        jieguo[j]=a[top]+"";
                        j++;
                        a[top]=str.charAt(i);
                    }
                    else if ("(".indexOf(a[top])>=0)
                    {
                        top++;
                        a[top]=str.charAt(i);
                    }
                    else if ("+-".indexOf(a[top])>=0)
                    {
                        jieguo[j]=a[top]+"";
                        j++;
                        a[top]=str.charAt(i);
                    }
                }
            }
        }
        for (;top!=-1;)
        {
            jieguo[j]=a[top]+"";
            j++;
            top--;
        }
        return jieguo;
    }
    public String yunsuan2(String str) 		//根号运算
    {
        String result="";
        double a=Double.parseDouble(str),b=0;
        b=Math.sqrt(a);
        result=String.valueOf(b);
        return result;
    }
    public String yunsuan3(String str)		//平方
    {
        String result="";
        double a=Double.parseDouble(str),b=0;
        b=Math.pow(a, 2);
        result=String.valueOf(b);
        return result;
    }
    public String yunsuan4(String str){		//阶乘
        String result = "";
        int a=Integer.parseInt(str),b=1;
        for(;a>0;a--){
            b*=a;
        }
        return String.valueOf(b);
    } public String yunsuan5(String str){		//sin
    String result = "";
    double a=Double.parseDouble(str),b=0;
    //b = a*Math.PI/180;
    b=Math.sin(Math.toRadians(a));
    result = String.valueOf(b);
    return result;
}
    public String yunsuan6(String str){		//cos
        String result = "";
        double a=Double.parseDouble(str),b=0;
        //b = a*Math.PI/180;
        b=Math.cos(Math.toRadians(a));
        result = String.valueOf(b);
        return result;
    }
    public String Result(String str[])
    {
        String Result[]=new String[100];
        int Top=-1;
        for (int i=0;str[i]!=null;i++)
        {
            if ("+-*%÷^".indexOf(str[i])<0)
            {
                Top++;
                Result[Top]=str[i];
            }
            if ("+-*%÷^".indexOf(str[i])>=0)
            {
                double x,y,n;
                x=Double.parseDouble(Result[Top]);
                Top--;
                y=Double.parseDouble(Result[Top]);
                Top--;
                if ("-".indexOf(str[i])>=0)
                {
                    n=y-x;
                    Top++;
                    Result[Top]=String.valueOf(n);
                }
                if ("+".indexOf(str[i])>=0)
                {
                    n=y+x;
                    Top++;
                    Result[Top]=String.valueOf(n);
                }
                if ("*".indexOf(str[i])>=0)
                {
                    n=y*x;
                    Top++;
                    Result[Top]=String.valueOf(n);
                }if ("^".indexOf(str[i])>=0)
            {
                n=Math.pow(y, x);
                Top++;
                Result[Top]=String.valueOf(n);
            }
                if ("÷".indexOf(str[i])>=0)
                {
                    if (x==0)
                    {
                        String s="ERROR";
                        return s;
                    }
                    else
                    {
                        n=y/x;
                        Top++;
                        Result[Top]=String.valueOf(n);
                    }
                }
                if ("%".indexOf(str[i])>=0)
                {
                    if (x==0)
                    {
                        String s="ERROR";
                        return s;
                    }
                    else

                    {
                        n=y%x;
                        Top++;
                        Result[Top]=String.valueOf(n);
                    }
                }
            }
        }
        return Result[Top];
    }
    public static void main(String arg[])
    {
        CalculateView a=new CalculateView();
    }
}
