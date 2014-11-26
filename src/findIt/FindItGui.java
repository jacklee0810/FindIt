package findIt;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * FindIt ����
 * @author SkyFire
 *
 */
class FindItGui extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ��ǰ��������
	 */
	private int thNum=0;
	/**
	 * �ؼ��������
	 */
	private JTextArea inKey=new JTextArea(2,15);
	/**
	 * ���ʽ�����
	 */
	private JTextArea inExpr=new JTextArea(2,80);
	/**
	 * Ŀ¼�����
	 */
	private JTextField inDir=new JTextField(15);
	/**
	 * &��ť
	 */
	private JButton andBt=new JButton("&");
	/**
	 * |��ť
	 */
	private JButton orBt=new JButton("|");
	/**
	 * ^��ť
	 */
	private JButton notBt=new JButton("^");
	/**
	 * ��� ��ť
	 */
	private JButton chooseBt=new JButton("���");
	/**
	 * ��ʼɸѡ��ť
	 */
	private JButton startBt=new JButton("��ʼɸѡ");
	/**
	 * ���� ��ť
	 */
	private JButton aboutBt=new JButton("����");
	/**
	 * �ؼ��� ��ʾ��ǩ
	 */
	private JLabel keyLb=new JLabel("�ؼ��֣�");
	/**
	 * ���ʽ ��ʾ��ǩ
	 */
	private JLabel allLb=new JLabel("���ʽ�������ļ�");
	/**
	 * ѡ��Ŀ¼ ��ʾ��ǩ
	 */
	private JLabel chooseLb=new JLabel("ѡ��Ŀ¼��");
	/**
	 * �߳�������ʾ��ǩ
	 */
	private JLabel thNumLb=new JLabel();
	/**
	 * ��־��ʾ
	 */
	private JTextArea msgOut=new JTextArea(null,null,4,50);
	/**
	 * ��־��ʾ ������
	 */
	private JScrollPane pane=new JScrollPane(msgOut);
	
	/**
	 * &��|��^��ť����
	 * @author SkyFire
	 *
	 */
	class andListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!inKey.getText().isEmpty()){
				inExpr.setText(inExpr.getText()+((JButton)e.getSource()).getText()+"\""+inKey.getText()+"\"");
				inKey.setText("");
			}
		}
	}
	/**
	 * ��� ��ť����
	 * @author SkyFire
	 *
	 */
	class chooseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChoose=new JFileChooser();
			fileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int value=fileChoose.showOpenDialog(FindItGui.this);
			if(value==JFileChooser.APPROVE_OPTION){
				inDir.setText(fileChoose.getSelectedFile().toString());
			}
		}
		
	}
	
	/**
	 * ��ʼɸѡ ��ť����
	 * @author SkyFire
	 *
	 */
	class startListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(inExpr.getText().toString().isEmpty()||inDir.getText().toString().isEmpty()){
				errorOut(null,null,"�뽫��Ϣ��д������",0);
			}else{
				++thNum;
				refThNum();
				receMsg(inExpr.getText(),inDir.getText(),"����");
				Thread t=new Thread(new startAna());
				t.start();
			}
		}
	}
	
	/**
	 * ���ڰ�ť����
	 * @author SkyFire
	 *
	 */
	class aboutListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame abt=new JFrame("����");
			abt.setResizable(false);
			abt.setBounds(200,100,800,600);
			abt.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			JTextArea aboutTxt=new JTextArea();
			aboutTxt.setEditable(false);
			aboutTxt.setLineWrap(true);
			/*
			 * ���� ����
			 */
			aboutTxt.setText("FindIt ˵���ĵ�\n\n" +
					"�汾������־:\n" +
					"	2014-11-25:֧�ֹؼ������л��з�,�Ż�GUI\n\n" +
					"һ����������\n" +
					"	ɸѡָ��Ŀ¼������ָ���������ĵ�\n\n"+
					"������ɫ\n" +
					"	1.������\n" +
					"		��ͬʱ���ж��ɸѡ����\n" +
					"	2.�߼�ɸѡ\n" +
					"		���Խ��ؼ���ͨ����&������|������^��������������߼�ɸѡ�������ͬʱ���С�������������ѧ��,���ǲ��������������ĵ��������������ʽ��" +
					"&\"����\"&\"��ѧ\"^\"�����\"\n" +
					"	3.֧�ֶ����ļ���ʽɸѡ\n" +
					"		Ŀǰ֧��.txt��.pdf��.doc��.docx��.ppt��.pptx��.xls��.xlsx�ļ���ʽ��ɸѡ\n" +
					"	4.֧������ɸѡ\n" +
					"		�ؼ��ֿ���Ϊ������ʽ���磺&\"����.*��ѧ\" ����ɸѡ�����С�����(�����ַ�)��ѧ�� �����н��\n" +
					"	5.������\n" +
					"		����һ��ؼ��ֿ����ڹؼ��ֿ�ֱ������ؼ��֣���������߼����Ű�ť�����ڱ��ʽ�����Զ����ɱ��ʽ���磺����ؼ���" +
					"������������� & ��ť�����ڱ��ʽ��������&\"����\"\n\n" +
					"�������ڰ�Ȩ\n" +
					"	����Ŀ���������չJAR��pdfbox-app-1.8.7.jar��xmlbeans-2.3.0.jar��poi-3.11-beta3-20141111.jar��" +
					"poi-examples-3.11-beta3-2.141111.jar��poi-excelant-3.11-beta3-20141111.jar��poi-ooxml-3.11-beta3-" +
					"20141111.jar��poi-ooxml-schemas-3.11-beta3-20141111.jar��poi-scratchpad-3.11-beta3-20141111.jar��\n" +
					"	���ϰ��İ�Ȩ����ԭ���ߣ��ڴ��򿪷������¾�����л���ǿ��������ǿ���jar\n" +
					"	����ĿΪ��Դ��Ŀ������ʱ��http://github.com/skyfireitdiy/FindIt��ȡ���µ�Դ���롣\n\n" +
					"--SkyFire");
			JScrollPane scoll=new JScrollPane(aboutTxt);
			abt.add(scoll);
			
			abt.setVisible(true);
		}
	}
	
	/**
	 * ˢ�� ����������Ϣ
	 */
	private void refThNum(){
		thNumLb.setText("��ǰ��������������:"+String.valueOf(thNum));
	}
	
	/**
	 * ���캯��
	 */
	FindItGui(){
		/*
		 * ���ø��������
		 */
		super("FindIt V1.1");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 300, 600, 350);
		this.setResizable(false);
		/*
		andBt.setSize(50, 30);
		orBt.setSize(50, 30);
		notBt.setSize(50, 30);
		startBt.setSize(200,30);
		chooseBt.setSize(50,30);
		aboutBt.setSize(50,30);
		msgOut.setEditable(false);
		msgOut.setLineWrap(true);
		msgOut.setFont(new Font(null,Font.PLAIN,12));
		msgOut.add(new JScrollPane());
		*/
		
		/*
		 * �������
		 */
		//setLayout(new FlowLayout());
		setLayout(null);
		this.add(allLb);
		this.add(inExpr);
		this.add(keyLb);
		this.add(inKey);
		this.add(andBt);
		this.add(orBt);
		this.add(notBt);
		this.add(chooseLb);
		this.add(inDir);
		this.add(chooseBt);
		this.add(aboutBt);
		this.add(thNumLb);
		this.add(startBt);
		this.add(pane);
		
		
		allLb.setBounds(10, 25,100, 30);
		inExpr.setBounds(120,10,450,60);
		keyLb.setBounds(10,95,50,30);
		inKey.setBounds(70,80,100,60);
		andBt.setBounds(180, 95, 50, 30);
		orBt.setBounds(240,95,50,30);
		notBt.setBounds(300,95,50,30);
		inDir.setBounds(360, 95, 150, 30);
		chooseBt.setBounds(520,95,60,30);
		aboutBt.setBounds(520, 160, 60, 30);
		thNumLb.setBounds(10, 160, 200, 30);
		startBt.setBounds(250, 160, 100, 30);
		pane.setBounds(10, 200, 580,120);
		/*
		 * ע���¼�����
		 */
		andBt.addActionListener(new andListener());
		orBt.addActionListener(new andListener());
		notBt.addActionListener(new andListener());
		chooseBt.addActionListener(new chooseListener());
		startBt.addActionListener(new startListener());
		aboutBt.addActionListener(new aboutListener());
		
		
		/*
		 * ��ʾ���
		 */
		this.setVisible(true);
		inExpr.setVisible(true);
		/*
		 * ˢ����������
		 */
		refThNum();
	}
	
	/**
	 * ��ȡ���ʽ
	 * @return ���ʽ���е�����
	 */
	String getExpression(){
		String str=inExpr.getText();
		inExpr.setText("");
		inKey.setText("");
		return str;
	};
	
	/**
	 * ��ȡĿ¼
	 * @return Ŀ¼������е�����
	 */
	String getDir(){
		String str=inDir.getText();
		inDir.setText("");
		return str;
	}
	
	/**
	 * ���������Ϣ
	 * @param exp ��ǰ���ʽ
	 * @param dir ��ǰĿ¼
	 * @param errMsg ������Ϣ
	 * @param level ����ȼ�
	 */
	void errorOut(String exp,String dir,String errMsg,int level){
		receMsg(exp,dir,"����"+errMsg+" �ȼ�:"+String.valueOf(level));
		thNum-=level;
		refThNum();
	}
	
	/**
	 * ���ɸѡ���
	 * @param exp ��ǰ���ʽ
	 * @param dir ��ǰĿ¼
	 * @param result ����б�
	 */
	void resultOut(String exp,String dir,ArrayList<String> result){
		--thNum;
		refThNum();
		receMsg(exp,dir,"�������");
		JFrame resOut=new JFrame("Ŀ¼:"+dir+" ���ʽ:"+exp+" ɸѡ���");
		resOut.setBounds(300,100,800,600);
		resOut.setResizable(false);
		JList<?> resList=new JList<Object>(result.toArray());
		resList.setFont(new Font(null,Font.PLAIN,15));
		resOut.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		resOut.add(resList);
		resOut.setVisible(true);
	}
	
	/**
	 * �����־��Ϣ
	 * @param exp ��ǰ���ʽ
	 * @param dir ��ǰĿ¼
	 * @param msg ��־��Ϣ
	 */
	void receMsg(String exp,String dir,String msg){
		msgOut.setText(msgOut.getText()+(new Date())+"Ŀ¼"+dir+" ���ʽ:"+exp+" �¼�:"+msg+"\n");
		disLast();
	}
	
	/**
	 * ɸѡ�߳�ԭ��
	 * @author SkyFire
	 *
	 */
	class startAna implements Runnable{
			@Override
			public void run() {
				FindItAnalysis FANLY=new FindItAnalysis();
				FANLY.startAnalysis(FindItGui.this);
			}
	}
	
	/**
	 * �Զ�������־
	 */
	private void disLast(){
		msgOut.setCaretPosition(msgOut.getText().length());
	}
}