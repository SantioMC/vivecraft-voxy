package org.vivecraft.gui.settings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import org.vivecraft.gameplay.screenhandlers.GuiHandler;
import org.vivecraft.gameplay.screenhandlers.KeyboardHandler;
import org.vivecraft.gui.framework.GuiVROptionButton;
import org.vivecraft.gui.framework.GuiVROptionsBase;
import org.vivecraft.gui.framework.VROptionEntry;
import org.vivecraft.settings.VRHotkeys;
import org.vivecraft.settings.VRSettings;

public class GuiHUDSettings extends GuiVROptionsBase
{
    private VROptionEntry[] hudOptions = new VROptionEntry[] {
            new VROptionEntry(VRSettings.VrOptions.HUD_HIDE),
            new VROptionEntry(VRSettings.VrOptions.HUD_LOCK_TO),
            new VROptionEntry(VRSettings.VrOptions.HUD_SCALE),
            new VROptionEntry(VRSettings.VrOptions.HUD_DISTANCE),
            new VROptionEntry(VRSettings.VrOptions.HUD_OCCLUSION),
            new VROptionEntry(VRSettings.VrOptions.HUD_OPACITY),
            new VROptionEntry(VRSettings.VrOptions.RENDER_MENU_BACKGROUND),
            new VROptionEntry(VRSettings.VrOptions.TOUCH_HOTBAR),
            new VROptionEntry(VRSettings.VrOptions.AUTO_OPEN_KEYBOARD),
            new VROptionEntry(VRSettings.VrOptions.MENU_ALWAYS_FOLLOW_FACE),
            new VROptionEntry(VRSettings.VrOptions.PHYSICAL_KEYBOARD, (button, mousePos) -> {
                KeyboardHandler.setOverlayShowing(false);
                return false;
            }),
            new VROptionEntry(VRSettings.VrOptions.GUI_APPEAR_OVER_BLOCK),
            new VROptionEntry(VRSettings.VrOptions.PHYSICAL_KEYBOARD_SCALE),
            new VROptionEntry("vivecraft.options.screen.menuworld.button", (button, mousePos) -> {
                Minecraft.getInstance().setScreen(new GuiMenuWorldSettings(this));
                return true;
            }),
            new VROptionEntry(VRSettings.VrOptions.PHYSICAL_KEYBOARD_THEME)
    };

    public GuiHUDSettings(Screen guiScreen)
    {
        super(guiScreen);
    }

    public void init()
    {
        this.vrTitle = "vivecraft.options.screen.gui";
        super.init(this.hudOptions, true);
        super.addDefaultButtons();
    }

    protected void loadDefaults()
    {
        super.loadDefaults();
        this.minecraft.options.hideGui = false;
    }

    protected void actionPerformed(AbstractWidget widget) {
        if (widget instanceof GuiVROptionButton) {
            GuiVROptionButton button = (GuiVROptionButton)widget;

            if (button.id == VRSettings.VrOptions.PHYSICAL_KEYBOARD_THEME.ordinal()) {
                KeyboardHandler.physicalKeyboard.init();
            }
            if (button.id == VRSettings.VrOptions.MENU_ALWAYS_FOLLOW_FACE.ordinal()) {
                GuiHandler.onScreenChanged(Minecraft.getInstance().screen, Minecraft.getInstance().screen, false);
            }
        }
    }
}
