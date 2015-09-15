import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
//import java.util.Comparator;


public class InterStr {

	public static boolean isNumber(String val) {
	    try {
	        Integer.parseInt(val);
	        return true;
	    } catch (NumberFormatException nfex) {
	        return false;
	    }
	}
	
	public static boolean isFloat(String val) {
	    try {
	        Float.parseFloat(val);
	        return true;
	    } catch (NumberFormatException nfex) {
	        return false;
	    }
	}
	
	public static boolean isItem(String Item, ArrayList<String> array) {
	    
	    //線形探索
	    for(String s : array)
	    	if(Item.equals(s)) return true;

		return false;
	}
	
	public static float getMedian(ArrayList<Float> data) {
		float median;
		if (data.size() == 0)	median = Float.NaN;
		else if (data.size() % 2 == 0)
			median = ((data.get(data.size() / 2 - 1) + data.get(data.size() / 2))) / ((float) 2.0);
		else
			median = data.get((int) (data.size() / 2));
		return median;
	}
	
	public static float getqu(ArrayList<Float> data, float pos) {
		int pos_l = (int) pos;
		int pos_u = pos_l + 1;
		float rem = pos - (float) pos_l;
		//内分
		return (float) (data.get(pos_l) * (1.0 - rem) + data.get(pos_u) * rem);
	}
	
	//dataはソート済み
	//summaに四分位数などが入る
	//中央値以下の集団の中央値，中央値以上の中央値はヒンジ値
	//EXCELのQUARTILE.INC関数のように比例分配をする
	public static void getfiv(ArrayList<Float> data, Float[] summa){
		
		float lq_pos;
		int lq_pos_i;
		
		float median_pos;
		int median_pos_i;
		
		float uq_pos;
		int uq_pos_i;

		if (data.size() == 0) {
			for (int i = 0; i < summa.length; i++)	summa[i] = Float.NaN;
			return;
		}
			
		switch(data.size() % 4){
			case 0:
					median_pos = (float) (data.size() / 2 - 0.5);
					lq_pos = (float) (median_pos / 2.0);
					uq_pos = (float) ((median_pos + data.size() - 1.0) / 2.0);
					summa[0] = data.get(0); //min
					summa[1] = getqu(data, lq_pos);
					summa[2] = getMedian(data);
					summa[3] = getqu(data, uq_pos);
					summa[4] = data.get(data.size()-1); //max
					break;
			case 1:
					median_pos_i = (int) (data.size() / 2);
					lq_pos_i = median_pos_i / 2;
					uq_pos_i = median_pos_i + lq_pos_i;
					summa[0] = data.get(0); // min
					summa[1] = data.get(lq_pos_i);
					summa[2] = getMedian(data);
					summa[3] = data.get(uq_pos_i);
					summa[4] = data.get(data.size() - 1); // max
					break;
			case 2:
					median_pos = (float) (data.size() / 2 - 0.5);
					lq_pos = (float) (median_pos / 2.0);
					uq_pos = (float) ((median_pos + data.size() - 1.0) / 2.0);
					summa[0] = data.get(0); // min
					summa[1] = getqu(data, lq_pos);
					summa[2] = getMedian(data);
					summa[3] = getqu(data, uq_pos);
					summa[4] = data.get(data.size() - 1); // max
					break;
			case 3:
					median_pos_i = (int) (data.size() / 2);
					lq_pos = (float) (median_pos_i / 2.0);
					uq_pos = (float) (median_pos_i + lq_pos);
					summa[0] = data.get(0); // min
					summa[1] = getqu(data, lq_pos);
					summa[2] = getMedian(data);
					summa[3] = getqu(data, uq_pos);
					summa[4] = data.get(data.size() - 1); // max
					break;
		}
		
		//ヒンジ値
//		if(data.size() == 0){
//			for(int i=0;i<summa.length;i++)summa[i] = Float.NaN; 
//		}
//		
//		else if(data.size() == 1){
//			for(int i=0;i<summa.length;i++)summa[i] = data.get(0); 
//		}
//		
//		//((int)(data.size() / 2)) / 2 - 1で-1になる
//		else if(data.size() == 3){
//			summa[0]=data.get(0);
//			summa[1]=data.get(0);
//			summa[2]=getMedian(data);
//			summa[3]=data.get(2);
//			summa[4]=data.get(2);
//		}		
//		
//		else if(data.size() % 2 == 0) {
//			summa[0]=data.get(0);
//			summa[1]=data.get((int)(data.size()/4));
//			summa[2]=getMedian(data);
//			summa[3]=data.get(data.size()/2+(int)(data.size()/4));
//			summa[4]=data.get(data.size()-1);
//		}
//		
//		else{
//			summa[0]=data.get(0);
//			summa[1]=(data.get(((int)(data.size() / 2)) / 2 - 1) + data.get(((int)(data.size() / 2)) / 2)) / ((float)2.0);
//			summa[2]=getMedian(data);
//			summa[3]=(data.get(((int)(data.size() / 2))+((int)(data.size() / 2)) / 2) + data.get(((int)(data.size() / 2))+((int)(data.size() / 2)) / 2 + 1)) / ((float)2.0);
//			summa[4]=data.get(data.size()-1);
//		}
		
	}

	public static void main(String[] args) {
//		ArrayList<Float> test_data = new ArrayList<Float>();
//		Float[] test_fiv = new Float[5];
//		for(int i=0;i<7;i++)test_data.add((float)i);
//		for(float d : test_data)System.out.print(d+" ");
//		System.out.println();
//		getfiv(test_data,test_fiv);
//		for(float d : test_fiv)System.out.print(d+" ");
//		System.out.println();
//		System.exit(0);
		
		// TODO 自動生成されたメソッド・スタブ
        FileReader[] in = new FileReader[3];
        BufferedReader[] br = new BufferedReader[3];
        ArrayList<String> array3 = new ArrayList<String>();
        ArrayList<String> array4 = new ArrayList<String>();
                
		try {
	       	File nowfile = new File("now.txt");
	       	
        	if (nowfile.createNewFile()) {
                //System.out.println("ファイルの作成に成功しました。");
            }
        	else {
                //System.out.println("ファイルの作成に失敗しました。");
        		if (nowfile.exists()){
        			nowfile.delete();
        			nowfile.createNewFile();
        		}
            }
	       	
	        String line;
	        String[] strAry;
	        in[0] = new FileReader(args[0]);
        	in[1] = new FileReader("Items.txt");
	        br[0] = new BufferedReader(in[0]);
        	br[1] = new BufferedReader(in[1]);
        	
        	//項目
        	while ((line = br[1].readLine()) != null) 
        		array3.add(line);
        	
        	in[2] = new FileReader("Numbers.txt");
        	br[2] = new BufferedReader(in[2]);
        	//目的地
        	while ((line = br[2].readLine()) != null) 
        		array4.add(line);
        	
        	for(String s : array3) System.out.println(s); //項目
        	for(String s : array4) System.out.println(s); //数値
        	
    		File fileA = new File("Interval.csv");
    		File fileB = new File("Stat.csv");
    		if (fileA.exists())
    			fileA.delete();
    		if (fileB.exists())
    			fileB.delete();
	        
	        FileWriter[] out = new FileWriter[2];
	        BufferedWriter[] bw = new BufferedWriter[2];
	        out[0] = new FileWriter("Interval.csv");
	        bw[0] = new BufferedWriter(out[0]);
	        
	        //五数要約されたデータが入ったファイル
	        out[1] = new FileWriter("Stat.csv");
	        bw[1] = new BufferedWriter(out[1]);
	        
	        Boolean[] flag = new Boolean[4];
	        int i, j;
	        
	        //ファイルに書き込む文字列
	        String file,file2;
	        //int f=0;
	        while ((line = br[0].readLine()) != null) {
	        	//f++;
				ArrayList<Float> array = new ArrayList<Float>();
				ArrayList<Float> array2 = new ArrayList<Float>();
				for (int h = 0; h < flag.length; h++)	flag[h] = false;

				System.out.println(line);
				strAry = line.split(" ");
				i = 0;
				j = 0;
				//書き込む文字列
				file = "";
				file2 = "";
	            
	            for(String s : strAry){
	            	//System.out.println(s + " " + isFloat(s));

	            	//項目の選定
					if (!flag[3]) {
						if (!isFloat(s) && isItem(s, array3)) 
							flag[3] = true;
					}

					//数字の選定
					if (!flag[1] && isFloat(s)) {
						flag[2] = isItem(s, array4);
						flag[1] = true; // 1回目の数字
					}
	            	
	            	if("Avg=".equals(s))	flag[0] = true;
           		            		
	            	if(flag[0] == true){
	            		if(isFloat(s)) i++;
	            		//4つ目から
	            		if(i >= 4){
	            			if(isFloat(s) && j % 2 == 0)
	            				array.add(Float.parseFloat(s));
	            			j++;
	            		}
	            	}
	            	else{
						if (!"".equals(s) && !" ".equals(s)){
	            			file += (s + ",");
	            			file2 +=  (s + ",");
						}
	            	}
	            }
	            
				if (flag[2] && flag[3]) {
					// System.out.println("fi" + file);
					// System.out.println(array.size());
					if (array.size() > 1) {
						// for(float f : array){
						// file += (f + " ");
						// //System.out.println(f);
						// }
						
						//間隔取得
						for (int k = 1; k < array.size(); k++) 
							array2.add(array.get(k) - array.get(k - 1));


						for (int k = 0; k < array2.size() - 1; k++)
							file += (array2.get(k) + ",");
						file += array2.get(array2.size() - 1);

						// System.out.println();
						// System.out.println("prev");

						// for (int k = 0; k < array2.size(); k++)
						// System.out.println(array2.get(k));
						// System.out.println("ya!" + array2.size());

						Collections.sort(array2);
						// System.out.println();
						// System.out.println("after");
						// for (int k = 0; k < array2.size(); k++)
						// System.out.println(array2.get(k));

						//System.out.println("中央値は・・・" + getMedian(array2));

						//五数要約されたデータが入る
						Float[] fiv = new Float[5];
						getfiv(array2, fiv);
						for (int k = 0; k < fiv.length - 1; k++)
							file2 += (fiv[k] + ",");
						file2 += fiv[fiv.length - 1];
					}
					// else if(array.size() == 1)
					// file += "";
					else{
						file += "-";
						file2 += "-";
					}
					// System.out.println("fi" + file);
					bw[0].write(file);
					bw[0].newLine();
					bw[1].write(file2);
					bw[1].newLine();
				}
            	//デバック
//    	        if(f>100) break;
	        }

        	bw[0].flush();
        	out[0].close();
        	bw[1].flush();
        	out[1].close();
        	br[0].close();
        	in[0].close();
        	
       		if (nowfile.exists())
    			nowfile.delete();
        	
//        	File newfile = new File("finished.txt");
//        	if (newfile.createNewFile()) {
//                //System.out.println("ファイルの作成に成功しました。");
//            }
//        	else {
//                //System.out.println("ファイルの作成に失敗しました。");
//        		if (newfile.exists()){
//        			newfile.delete();
//        			newfile.createNewFile();
//        		}
//            }
	        
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}

}
