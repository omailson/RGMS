package aspects;

import br.ufpe.cin.rgms.base.Controle;
import br.ufpe.cin.rgms.base.Persistence;

public aspect Transaction {

	pointcut transaction() : 
		(execution(* *(..))
		&& within(Controle+)
		&& !execution(Controle+.new(..)))
		&& !execution(* validar(..));
	
	before(): transaction() {
		Persistence.getInstance().beginTransaction();
	}
	
	after(): transaction() {
		Persistence.getInstance().commit();
	}
}
