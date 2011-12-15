
 
package valueObjects
{
	import mx.collections.ArrayCollection;

[Bindable]
[RemoteClass(alias="kanbanbackend.model.Workspace")]
public class Workspace
{
	public var stages:ArrayCollection;
	public var postitFont:String;
	public var postitStyle:String;
	public var postitWeight:String;
	public var postitSize:int;
	public var postitColor:int;
	public var numColumns:int;
	public var id:int;
	public var name:String;
	public var versionID:int;
	public var projectID:int;
}
    

}