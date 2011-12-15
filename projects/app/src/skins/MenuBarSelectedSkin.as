package skins
{
	import mx.skins.ProgrammaticSkin;
	import flash.display.Graphics;
	import mx.utils.ColorUtil;
	public class MenuBarSelectedSkin extends ProgrammaticSkin
	{
		public function MenuBarSelectedSkin()
		{
			super ();
		}
		override protected function updateDisplayList( w: Number, h: Number):void
		{
			var backgroundAlpha: Number = getStyle ( "backgroundAlpha");
			var selectionColor: uint = getStyle ( "selectionColor");
			graphics.clear ();
			drawRoundRect(0,0, w, h , 5, selectionColor, backgroundAlpha );
		}
	}
}