package shareonfoot;




import org.json.simple.*;
import org.json.simple.parser.JSONParser;
public class test {


 public static void main(String[] args) {
  try {
   String jsonInfo = "{\"books\":[{\"genre\":\"�Ҽ�\",\"price\":\"100\",\"name\":\"����� �������� ��°�?\",\"writer\":\"�罺����\",\"publisher\":\"�罺���� ���ǻ�\"},{\"genre\":\"�Ҽ�\",\"price\":\"300\",\"name\":\"ȫ�浿��\",\"writer\":\"���\",\"publisher\":\"��� ���ǻ�\"},{\"genre\":\"�Ҽ�\",\"price\":\"900\",\"name\":\"���������\",\"writer\":\"���丣 ����\",\"publisher\":\"���丣 ���� ���ǻ�\"}],\"persons\":[{\"nickname\":\"���ùμ�\",\"age\":\"25\",\"name\":\"�۰�ȣ\",\"gender\":\"����\"},{\"nickname\":\"������\",\"age\":\"21\",\"name\":\"������\",\"gender\":\"����\"}]}";
   JSONParser parser = new JSONParser();
   JSONObject jsonObject = (JSONObject)parser.parse(jsonInfo);
   JSONArray jsonArray = (JSONArray)jsonObject.get("books");
   System.out.println("***************");
   System.out.println("size :  "+ jsonArray.size());
   System.out.println("***************");
   
  } catch (Exception e) {
   e.printStackTrace();
  }



 }
 
 
}

