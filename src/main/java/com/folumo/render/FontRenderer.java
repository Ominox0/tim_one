package com.folumo.render;

import com.sun.jna.Memory;
import io.github.libsdl4j.api.surface.SDL_Surface;
import io.github.libsdl4j.api.pixels.SDL_PixelFormatEnum;
import io.github.libsdl4j.api.surface.SdlSurface;
import io.github.libsdl4j.api.error.SdlError;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FontRenderer {
    private Font font;

    public FontRenderer(String fontPath, float size) {
        try {
            if (fontPath != null) {
                font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(size);
            } else {
                font = new Font("Arial", Font.PLAIN, (int) size);
            }
        } catch (FontFormatException | IOException e) {
            System.err.println("Failed to load font. Falling back to Arial.");
            font = new Font("Arial", Font.PLAIN, (int) size);
        }
    }

    public SDL_Surface renderText(String text, Color color) {
        if (text == null || text.isEmpty()) return null;

        // --- Measure text size ---
        BufferedImage tmpImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gtmp = tmpImg.createGraphics();
        gtmp.setFont(font);
        FontMetrics fm = gtmp.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        int ascent = fm.getAscent();
        gtmp.dispose();

        // --- Draw actual image ---
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setFont(font);
        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.drawString(text, 0, ascent);
        g2d.dispose();

        // Convert JPEG/ARGB → raw pixel memory for SDL
        int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);

        Memory nativeMem = new Memory((long) width * height * 4);

        for (int i = 0; i < pixels.length; i++) {
            nativeMem.setInt((long) i * 4, pixels[i]);
        }

        SDL_Surface surface = SdlSurface.SDL_CreateRGBSurfaceWithFormatFrom(
                nativeMem,
                width,
                height,
                32,
                width * 4,
                SDL_PixelFormatEnum.SDL_PIXELFORMAT_ARGB8888
        );

        if (surface == null) {
            throw new RuntimeException("Failed to create surface: " + SdlError.SDL_GetError());
        }

        return surface;
    }
}
