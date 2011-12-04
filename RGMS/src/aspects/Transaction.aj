package aspects;

import br.ufpe.cin.rgms.base.Controle;
import br.ufpe.cin.rgms.base.Persistence;

public aspect Transaction {

	pointcut transaction() : 
		execution(* *(..))
		&& within(Controle)
		&& !withincode(Controle.new(..));
	
	before(): transaction() {
		Persistence.getInstance().beginTransaction();
	}
	
	after(): transaction() {
		Persistence.getInstance().commit();
	}
}
