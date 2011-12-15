/**
 * This is a generated sub-class of _KanbanService1.as and is intended for behavior
 * customization.  This class is only generated when there is no file already present
 * at its target location.  Thus custom behavior that you add here will survive regeneration
 * of the super-class. 
 **/
 
package services
{

public class KanbanService extends _Super_KanbanService
{
	private static var instance:KanbanService;
	
	
	public static function getInstance():KanbanService {
		if(instance == null) {
			instance = new KanbanService(); 
		}
		return instance;
	}
               
}

}
