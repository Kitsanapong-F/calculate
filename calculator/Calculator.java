package calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        logic logic = new logic();  // สร้างอ็อบเจ็กต์ logic สำหรับการคำนวณ
        display display = new display(); // สร้างอ็อบเจ็กต์ display สำหรับการแสดงผล

        boolean running = true; // ตัวแปรสำหรับควบคุมลูปหลัก
        
        while (running) { // ลูปหลักสำหรับเริ่มการคำนวณใหม่
            System.out.println("\n---Calculator---");
            System.out.print("num: "); // ขอให้ผู้ใช้ป้อนตัวเลขตัวแรก

            // ดักจับกรณีผู้ใช้ไม่ป้อนตัวเลข
            while (!input.hasNextDouble()) {
                System.out.println("กรุณาป้อนตัวเลขเท่านั้น!");
                System.out.print("num: ");
                input.next();
            }

            double num = input.nextDouble();
            logic.addNum(num);// เพิ่มตัวเลขตัวแรกเข้าไปใน logic

            while (true) { // ลูปสำหรับป้อนตัวดำเนินการไปเรื่อยๆ
                input.nextLine(); // clear buffer
                System.out.print("ตัวดำเนินการ (+, -, *, /, %) หรือ '=' เพื่อดูคำตอบ: ");

                while (!input.hasNext("[+*/%=-]")) { //[...] หมายถึงเลือกแค่ตัวเดียวจากด้านในนี้

                    System.out.println("กรุณาป้อนตัวดำเนินการเท่านั้น!");
                    System.out.print("ตัวดำเนินการ (+, -, *, /, %) หรือ '=' เพื่อดูคำตอบ: ");
                    input.nextLine();
                }

                String op = input.nextLine();
                op = op.trim();//ตัดช่องว่าง

                if (op.equals("=")) {// ถ้าผู้ใช้ป้อน = ให้หยุดลูปนี้เพื่อคำนวณผลลัพธ์
                    break;
                }
                
                char sop = op.charAt(0); // ดึงตัวอักษรตัวแรกมาเป็นตัวดำเนินการ
                logic.addOp(sop); // เพิ่มตัวดำเนินการเข้าไปใน logic

                System.out.print("num: "); // ขอให้ผู้ใช้ป้อนตัวเลขถัดไป

                while (!input.hasNextDouble()) {  // ดักจับกรณีผู้ใช้ไม่ป้อนตัวเลข
                    System.out.println("กรุณาป้อนตัวเลขเท่านั้น!");
                    System.out.print("num: ");
                    input.next(); 
                }

                num = input.nextDouble(); // รับตัวเลขถัดไป
                logic.addNum(num); // เพิ่มตัวเลขถัดไปเข้าไปใน logic

                display.printProcess(logic); // แสดงกระบวนการคำนวณปัจจุบัน
            }

            if (!logic.calFirst()) { // ตรวจสอบว่าการคำนวณสำเร็จหรือไม่
                display.printProcess(logic); // แสดงกระบวนการคำนวณสุดท้าย
                System.out.println("Result: " + logic.calSum()); // แสดงผลลัพธ์
            } else { // ถ้าการคำนวณไม่สำเร็จ
                System.out.println("!!! เกิดข้อผิดพลาดในการคำนวณ (เช่น หารด้วย 0) !!!");
            }
            // ทำให้การต้องตอบ Y / N เท่านั้นถึงจะทำงานต่อไปได้
            // --- ฟีเจอร์ถามว่าจะใช้ต่อไหม ---
            while (true) {
                System.out.print("\nWould you like to continue to use calculator or not (y/n): ");
                String choice = input.next().toLowerCase(); // รับคำตอบจากผู้ใช้และแปลงเป็นตัวพิมพ์เล็ก

                if (choice.equalsIgnoreCase("N")) { // ถ้าผู้ใช้ป้อน N ให้หยุดการทำงานของโปรแกรม
                    running = false;
                    break;
                } else {
                    if(choice.equalsIgnoreCase("Y")){ // ถ้าผู้ใช้ป้อน Y ให้เริ่มการคำนวณใหม่
                        logic.clear(); // ล้างข้อมูลเก่าเพื่อเริ่มการคำนวณใหม่
                        break;
                    }
                }
            }
        }

        display.thankYou();

        input.close();
    }
}