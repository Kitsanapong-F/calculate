package Calculator;

import java.util.ArrayList; // นำเข้า ArrayList
import java.util.List; // นำเข้า List

public class logic {
    private ArrayList<Double> num; // รายการเก็บตัวเลข
    private ArrayList<Character> op; // รายการเก็บตัวดำเนินการ

    public logic() { //constructor
        this.num = new ArrayList<>();
        this.op = new ArrayList<>();
    }

    public void addNum(double n) { // เพิ่มตัวเลขเข้าไปในรายการ
        this.num.add(n);
    }

    public void addOp(char o) { // เพิ่มตัวดำเนินการเข้าไปในรายการ
        this.op.add(o);
    }

    public List<Double> getNum() { return num; } // ดึงรายการตัวเลขออกมา
    
    public List<Character> getOp() { return op; }// ดึงรายการตัวดำเนินการออกมา

    // รอบที่ 1: จัดการ *, /, %
    public boolean calFirst() {
        boolean hasError = false;// ตัวแปรเพื่อตรวจสอบข้อผิดพลาด เช่น การหารด้วย 0

        for (int i = 0; i < op.size(); i++) { // วนลูปผ่านตัวดำเนินการทั้งหมด
            char currentOp = op.get(i);// ดึงตัวดำเนินการปัจจุบัน
            if (currentOp == '%' || currentOp == '/' || currentOp == '*') { // ถ้าตัวดำเนินการเป็น *, /, %
                double left = num.get(i); // ดึงตัวเลขทางซ้าย
                double right = num.get(i + 1); // ดึงตัวเลขทางขวา
                double result = 0; // ตัวแปรเก็บผลลัพธ์ชั่วคราว

                if (currentOp == '*') { // การคูณ
                    result = left * right;
                } else {
                    if (right == 0) { // ตรวจสอบการหารด้วย 0
                        hasError = true; // ตั้งค่าว่ามีข้อผิดพลาด
                        break;
                    }
                    result = (currentOp == '/') ? left / right : left % right; // การหารหรือหารเอาเศษ
                }

                num.set(i, result);// อัปเดตตัวเลขทางซ้ายด้วยผลลัพธ์
                num.remove(i + 1);// ลบตัวเลขทางขวาที่เพิ่งคำนวณแล้ว
                op.remove(i); // ลบตัวดำเนินการที่เพิ่งคำนวณแล้ว
                i--; // ถอยดัชนีกลับมาเช็คตำแหน่งเดิมที่เพิ่งเลื่อนมา
            }
        }
        return hasError;// คืนค่าข้อผิดพลาด (ถ้ามี)
    }

    // รอบที่ 2: จัดการ +, -
    public double calSum() {
        double sum = num.get(0);
        for (int i = 0; i < op.size(); i++) {
            if (op.get(i) == '+') sum += num.get(i + 1);
            else if (op.get(i) == '-') sum -= num.get(i + 1);
        }
        return sum;
    }

    public void clear() {
        num.clear();
        op.clear();
    }
}
