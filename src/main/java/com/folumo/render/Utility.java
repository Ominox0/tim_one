package com.folumo.render;

import com.sun.jna.Memory;
import io.github.libsdl4j.api.pixels.SDL_PixelFormatEnum;
import io.github.libsdl4j.api.render.SDL_Renderer;
import io.github.libsdl4j.api.render.SDL_Texture;
import io.github.libsdl4j.api.surface.SDL_Surface;

import java.awt.*;
import java.awt.image.BufferedImage;

import static io.github.libsdl4j.api.render.SdlRender.SDL_CreateTextureFromSurface;
import static io.github.libsdl4j.api.render.SdlRender.SDL_SetRenderDrawColor;
import static io.github.libsdl4j.api.surface.SdlSurface.SDL_CreateRGBSurfaceWithFormatFrom;
import static io.github.libsdl4j.api.surface.SdlSurface.SDL_FreeSurface;

public class Utility {
    public static void setRenderColor(SDL_Renderer renderer, Color color){
        SDL_SetRenderDrawColor(renderer, (byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue(), (byte) color.getAlpha());
    }

    public static SDL_Texture bufferedImageToTexture(BufferedImage img, SDL_Renderer renderer) {
        int width = img.getWidth();
        int height = img.getHeight();

        int[] pixels = new int[width * height];
        img.getRGB(0, 0, width, height, pixels, 0, width);

        Memory nativeMem = new Memory((long) width * height * 4);

        for (int i = 0; i < pixels.length; i++) {
            nativeMem.setInt((long) i * 4, pixels[i]);
        }

        SDL_Surface surface = SDL_CreateRGBSurfaceWithFormatFrom(
                nativeMem,
                width,
                height,
                32,
                width * 4,
                SDL_PixelFormatEnum.SDL_PIXELFORMAT_ARGB8888
        );

        SDL_Texture texture = SDL_CreateTextureFromSurface(renderer, surface);
        SDL_FreeSurface(surface);

        return texture;
    }

    public static BufferedImage createTextImage(String text, Color color, Font font) {
        BufferedImage tmp = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = tmp.createGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(font);
        g2d.setColor(color);
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();

        return image;
    }

    public static long dot(long Xx, long Xy, long Yx, long Yy){
        return Xx * Xy + Yx * Yy;
    }

    public static boolean checkCollision(int x1, int x2, int y1, int y2, int w1, int w2, int h1, int h2){
        return (x1 < x2 + w2 &&
                x1 + w1 > x2 &&
                y1 < y2 + h2 &&
                y1 + h1 > y2);
    }
}
