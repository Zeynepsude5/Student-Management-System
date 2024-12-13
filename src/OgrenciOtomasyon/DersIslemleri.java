package OgrenciOtomasyon;
import java.util.List;

public interface DersIslemleri {
	
	void dersEkle(String ders);
	
	void dersSil(String ders);
	
	void dersOnayla(String ders);
	
	List<String> dersListesi();

}
