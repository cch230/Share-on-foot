package shareonfoot;




import org.json.simple.*;
import org.json.simple.parser.JSONParser;
public class test {


 public static void main(String[] args) {
  try {
   String jsonInfo = "{\"books\":[{\"genre\":\"소설\",\"price\":\"100\",\"name\":\"사람은 무엇으로 사는가?\",\"writer\":\"톨스토이\",\"publisher\":\"톨스토이 출판사\"},{\"genre\":\"소설\",\"price\":\"300\",\"name\":\"홍길동전\",\"writer\":\"허균\",\"publisher\":\"허균 출판사\"},{\"genre\":\"소설\",\"price\":\"900\",\"name\":\"레미제라블\",\"writer\":\"빅토르 위고\",\"publisher\":\"빅토르 위고 출판사\"}],\"persons\":[{\"nickname\":\"남궁민수\",\"age\":\"25\",\"name\":\"송강호\",\"gender\":\"남자\"},{\"nickname\":\"예니콜\",\"age\":\"21\",\"name\":\"전지현\",\"gender\":\"여자\"}]}";
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

