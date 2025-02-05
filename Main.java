import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // พฤติกรรมเริ่มต้น (ก่อน Thread.sleep()):
        // เธรดมีแนวโน้มที่จะทำงานอย่างรวดเร็วและพร้อมกันในระดับหนึ่ง เนื่องจาก
        // การดำเนินการนั้นรวดเร็วมาก เธรดหนึ่งอาจให้ผลลัพธ์รวดเร็วกว่า โดยพิมพ์ลำดับตัวเลขจำนวนมากก่อนที่เธรดอื่น
        // ดังนั้นลำดับที่แน่นอนจะแตกต่างกันไป
        // ระหว่างการรันเนื่องจากลักษณะของการกำหนดตารางเธรด แต่มักจะเป็นแบบเป็นก้อนๆ
        // โดยที่เธรดหนึ่งทำงานจำนวนมาก จากนั้นอีกเธรดหนึ่งทำต่อ และอื่นๆ
        // ผลลัพธ์ที่ได้จะดูเหมือนมีการสลับกันน้อยลง

        // หลังจากเพิ่ม Thread.sleep() พร้อมการหน่วงเวลาแบบสุ่ม:
        // ตอนนี้เธรดต่างๆ จะมีการสลับกันมากขึ้นอย่างเห็นได้ชัด การหน่วงเวลาแบบสุ่มทำให้เกิดความแปรปรวนในจังหวะการทำงาน ทำให้เธรดต่างๆ ผลัดกันทำงาน
        // บ่อยขึ้น ผลลัพธ์ที่ได้จะดูผสมปนเปกันมากกว่าตอนแรก โดยตัวเลขจากเธรดต่างๆ
        // ปรากฏในลำดับที่สุ่มมากขึ้น
        // คำอธิบายเกี่ยวกับการทำงานแบบ Multithreading และการเปลี่ยนแปลงลำดับ:
        // การทำงานแบบ Multithreading ใน Java อนุญาตให้ส่วนต่างๆ ของโปรแกรม
        // ทำงานพร้อมกัน Java Virtual Machine (JVM) จัดการเธรดต่างๆ

        Thread t1 = new Thread(() -> printNumbers(1));
        Thread t2 = new Thread(() -> printNumbers(2));
        Thread t3 = new Thread(() -> printNumbers(3));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printNumbers(int threadNumber) {
        Random random = new Random();
        for (int i = 1; i <= 50; i++) {
            System.out.println("thread #" + threadNumber + ": " + i);
            try {
                Thread.sleep(random.nextInt(50)); // หน่วงเวลาแบบสุ่มระหว่าง 0 ถึง 49 มิลลิวินาที
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}