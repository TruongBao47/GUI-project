import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.HashMap;

public class QuanLyBida {

    public static void main(String[] args) {
        // tao cua so chinh
        JFrame cuaSoChinh = new JFrame("Quản Lý Quán Bida");
        cuaSoChinh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // tat han khi bam close
        cuaSoChinh.setSize(800, 600);

        // tao cau truc du lieu luu trang thai cua 10 ban bida
        HashMap<Integer, LocalDateTime> trangThaiBan = new HashMap<>();

        // Gridlayout chia 10 ban bida
        JPanel bangBan = new JPanel();
        bangBan.setLayout(new GridLayout(2, 5, 10, 10));

        // tao 10 nut bida
        for (int soBan = 1; soBan <= 10; soBan++) {
            final int soBanCuoi = soBan; // Đảm bảo biến là effectively final
            JButton nutBan = new JButton("Bàn " + soBan);

            nutBan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!trangThaiBan.containsKey(soBanCuoi)) {
                        // bat dau tinh tien
                        trangThaiBan.put(soBanCuoi, LocalDateTime.now());
                        nutBan.setBackground(Color.GREEN);
                        nutBan.setText("Bàn " + soBanCuoi + " (Đang chơi)");
                    } else {
                        // tinh tien va ket thuc
                        LocalDateTime thoiGianBatDau = trangThaiBan.get(soBanCuoi);
                        LocalDateTime thoiGianKetThuc = LocalDateTime.now();
                        Duration thoiLuong = Duration.between(thoiGianBatDau, thoiGianKetThuc);
                        double soGio = thoiLuong.toMinutes() / 60.0;
                        double giaMoiGio = 50000;
                        double tongTien = soGio * giaMoiGio;

                        JOptionPane.showMessageDialog(cuaSoChinh, "Bàn " + soBanCuoi + " đã chơi " + String.format("%.2f", soGio) + " giờ.\nTổng tiền: " + tongTien + " VND", "Thanh toán", JOptionPane.INFORMATION_MESSAGE);

                        // Reset trang thai ban
                        trangThaiBan.remove(soBanCuoi);
                        nutBan.setBackground(null);
                        nutBan.setText("Bàn " + soBanCuoi);
                    }
                }
            });

            bangBan.add(nutBan);
        }


        cuaSoChinh.setLayout(new BorderLayout());
        cuaSoChinh.add(new JLabel("QUẢN LÝ QUÁN BIDA", JLabel.CENTER), BorderLayout.NORTH);
        cuaSoChinh.add(bangBan, BorderLayout.CENTER);

        // hien thi cua so
        cuaSoChinh.setVisible(true);
    }
}
