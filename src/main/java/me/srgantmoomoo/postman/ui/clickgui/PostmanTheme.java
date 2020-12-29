package me.srgantmoomoo.postman.ui.clickgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.theme.ColorScheme;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.RendererBase;
import com.lukflug.panelstudio.theme.Theme;

/**
 * weewee
 * @author SrgantMooMoo
 */
public class PostmanTheme implements Theme {
	protected ColorScheme scheme;
	protected Renderer componentRenderer,containerRenderer,panelRenderer;
	
	public PostmanTheme (ColorScheme scheme, int height, int border) {
		this.scheme=scheme;
		panelRenderer=new ComponentRenderer(9,height,border);
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

	
	protected class ComponentRenderer extends RendererBase {
		protected final int level,border;
		
		public ComponentRenderer (int level, int height, int border) {
			super(height+1,1,1);
			this.level=level;
			this.border=border;
		}

		@Override
		public void renderRect (Context context, String text, boolean focus, boolean active, Rectangle rectangle, boolean overlay) {
			Color color=getMainColor(focus,active);
			context.getInterface().fillRect(rectangle,color,color,color,color);
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
				Color color=getBackgroundColor(focus);
				context.getInterface().fillRect(context.getRect(),color,color,color,color);
			}

		@Override
		public void renderBorder (Context context, boolean focus, boolean active, boolean open) {
		}

		@Override
		public Color getMainColor (boolean focus, boolean active) {
			Color color;
			if (active) color=getColorScheme().getActiveColor();
			else color=getColorScheme().getBackgroundColor();
			if (!active && level<2) color=getColorScheme().getInactiveColor();
			color=new Color(color.getRed(),color.getGreen(),color.getBlue(),getColorScheme().getOpacity());
			return color;
		}

		@Override
		public Color getBackgroundColor (boolean focus) {
			return new Color(103,167,221,255);
		}

		@Override
		public ColorScheme getDefaultColorScheme() {
			return PostmanTheme.this.scheme;
		}
	}
}