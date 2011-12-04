package aspects;

public aspect AspectTest {

	pointcut testGet() : set(* *);
	
	after(): testGet(){
		System.out.println("TEST GET");
	}
}
