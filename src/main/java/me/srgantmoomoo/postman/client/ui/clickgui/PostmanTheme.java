package me.srgantmoomoo.postman.client.ui.clickgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.ColorScheme;
import com.lukflug.panelstudio.theme.DescriptionRenderer;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.RendererBase;
import com.lukflug.panelstudio.theme.Theme;

/**
 * @author SrgantMooMoo
 */
public class PostmanTheme implements Theme {
	protected ColorScheme scheme;
	protected Renderer componentRenderer,containerRenderer,panelRenderer;
	protected DescriptionRenderer descriptionRenderer;
	
	public PostmanTheme (ColorScheme scheme, int height, int border) {
		this.scheme=scheme;
		panelRenderer=new ComponentRenderer(0,height,border);
		containerRenderer=new ComponentRenderer(1,height,border);
		componentRenderer=new ComponentRenderer(2,height,border);
	}
	
	@Override
	public Renderer getPanelRenderer() {
		return panelRenderer;
	}

	@Override
	public Renderer getContainerRenderer() {
		return containerRenderer;
	}

	@Override
	public Renderer getComponentRenderer() {
		return componentRenderer;
	}
	
	public DescriptionRenderer getDescription() {
		return descriptionRenderer;
	}

	
	protected class ComponentRenderer extends RendererBase {
		protected final int level,border;
		
		public ComponentRenderer (int level, int height, int border) {
			super(height+1,0,0,0,0);
			this.level=level;
			this.border=border;
		}

		@Override
		public void renderRect (Context context, String text, boolean focus, boolean active, Rectangle rectangle, boolean overlay) {
			Color color=getMainColor(focus,active);
			Color color2=getBackgroundColor(focus);
			if (level==1 && !active)context.getInterface().fillRect(context.getRect(),color2,color2,color2,color2);
			else context.getInterface().fillRect(rectangle,color,color,color,color);
			if (overlay) {
				Color overlayColor;
				if (context.isHovered()) {
					overlayColor=new Color(255,255,255,64);
				} else {
					overlayColor=new Color(255,255,255,0);
				}
				context.getInterface().fillRect(context.getRect(),overlayColor,overlayColor,overlayColor,overlayColor);
			}
			Point stringPos=new Point(rectangle.getLocation());
			stringPos.translate(0,border);
			context.getInterface().drawString(stringPos,text,getFontColor(focus));
		}

		@Override
		public void renderBackground (Context context, boolean focus) {
				//Color color=getBackgroundColor(focus);
				//context.getInterface().fillRect(context.getRect(),color,color,color,color);
			}

		@Override
		public void renderBorder (Context context, boolean focus, boolean active, boolean open) {
			Color color;
			color=getDefaultColorScheme().getOutlineColor();
			if (level==1 && open) {
				context.getInterface().fillRect(new Rectangle(new Point(context.getPos().x+context.getSize().width - 1,14 + context.getPos().y),new Dimension(1,context.getSize().height - 15)),color,color,color,color);
			}
		}
		
		//public void renderDescription (Context context) {
		//}
	
		@Override
		public Color getMainColor (boolean focus, boolean active) {
			Color color;
			// active modules
			if (active && level>0) color=getColorScheme().getActiveColor();
			// background
			else color=getColorScheme().getBackgroundColor();
			// inactive modules
			if (!active && level<2) color=getColorScheme().getBackgroundColor();
			// category
			if (active && level<1) color=getColorScheme().getActiveColor();
			color=new Color(color.getRed(),color.getGreen(),color.getBlue(),getColorScheme().getOpacity());
			return color;
		}

		@Override
		public Color getBackgroundColor (boolean focus) {
			Color color;
			color=getColorScheme().getInactiveColor();
			color=new Color(color.getRed(),color.getGreen(),color.getBlue(),200);
			return color;
		}

		@Override
		public ColorScheme getDefaultColorScheme() {
			return PostmanTheme.this.scheme;
		}
	}
}