package calculator;

import java.util.List;

public class display {
    public void printProcess(logic log) {
        List<Double> number = log.getNum();  // ดึงรายการตัวเลขจาก log
        List<Character> oplist = log.getOp(); // ดึงรายการตัวดำเนินการจาก log
        
        if (number.isEmpty()) return; // ตรวจสอบว่ารายการตัวเลขว่างหรือไม่

        System.out.print("[Process]: ");
        for (int i = 0; i < oplist.size(); i++) {// วนลูปผ่านตัวดำเนินการทั้งหมด
            System.out.print(number.get(i) + " " + oplist.get(i) + " "); // พิมพ์ตัวเลขและตัวดำเนินการสลับกัน
        }
        // พิมพ์ตัวเลขตัวสุดท้ายที่ไม่มีตัวดำเนินการต่อท้าย
        System.out.println(number.get(number.size() - 1));
    }

    public void showResult(double result) { // แสดงผลลัพธ์สุดท้าย
        System.out.println("================================");
        System.out.println("ANSWER: " + result); // แสดงผลลัพธ์
        System.out.println("================================");
    }

    public void thankYou(){
        System.out.println("ขอบคุณที่ใช้งาน");
    }
}
