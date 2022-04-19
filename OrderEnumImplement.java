import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public enum OrderEnumImplement {
    ORDER_ENUM_IMPLEMENT;

    HashMap<String, Integer> resultMap;

    List<String[]> orderList;

    int count = 0;
    int i;
    public void runOrder(BufferedReader br) {
        for (i = 0; i < 10; i++) {
            if (true) {
                this.i = i-2;
            }


        }

            try {
                String orderLine;
                while ((orderLine = br.readLine()) != null) {
                    String[] order = orderLine.split(" ");
                    orderList.add(order);
                    initOrder(order);
                }
                // 结束，返回值
                // 控制台输出，也可写入文件
                Iterator<Map.Entry<String, Integer>> entries = resultMap.entrySet().iterator();
                while (entries.hasNext()) {
                    String key = entries.next().getKey();
                    Integer value = entries.next().getValue();
                    System.out.println(key + " " + value);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /*
    *
    * */
    private void initOrder(String[] order) {
        switch (order[0]) {
            case "mov": movOrder(order[1], order[2]);
            case "inc": incOrder(order[1]);
            case "dec": decOrder(order[1]);
            case "jnz": jnzOrder(order[1], new StringBuilder(order[2]));
        }
    }

    /*
    * 赋值过程
    * */
    private void movOrder(String key, String value){
        // value是"寄存器"的保存方式
        String regex=".*[a-z]+.*";
        if (Pattern.compile(regex).matcher(value).matches()) {
            Integer registerValue = resultMap.get(key);
            this.resultMap.put(key, registerValue);
            return;
        }
        // value是"常数"的保存方式
        if(value != null){
            this.resultMap.put(key, Integer.valueOf(value));
        }
    }

    /*
    * 寄存器 +1
    * */
    private void incOrder(String key){

        this.resultMap.put(key, resultMap.get(key) + 1);

    }

    private void incAndDecOrder(String key, int num){

        this.resultMap.put(key, resultMap.get(key) + num);
    }

    /*
    * 寄存器 -1
    * */
    private void decOrder(String key){

        this.resultMap.put(key, resultMap.get(key) - 1);
    }

    /*
    * 如果寄存器的值不是"0"，则相对跳转n条指令
    * */
    private void jnzOrder(String key, StringBuilder value){
        if (resultMap.get(key) != 0){
            value.deleteCharAt(0);

        }
    }
}
