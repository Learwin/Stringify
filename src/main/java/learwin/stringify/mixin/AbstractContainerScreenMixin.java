package learwin.stringify.mixin;

import learwin.stringify.client.LocalDelimiter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin<T extends AbstractContainerMenu> extends Screen implements MenuAccess<T> {

    protected AbstractContainerScreenMixin(Component pTitle) {
        super(pTitle);
    }

    @Inject(at = @At("TAIL"), method = "init()V")
    protected void init(CallbackInfo ci) {
        addRenderableWidget(new Button.Builder(Component.translatable("stringify.button"), pButton -> {
            NonNullList<Slot> slots = getMenu().slots;
            LinkedHashSet<Item> hashSet = new LinkedHashSet<>();
            List<String> itemList = new ArrayList<>();
            for (Slot slot : slots) {
                if (!slot.hasItem()) continue;
                Item item = slot.getItem().getItem();
                if (LocalDelimiter.shouldIgnoreDuplicates() || hashSet.add(item))
                    itemList.add(item.toString());
            }
            String delimiter = " ";
            if (!LocalDelimiter.getDelimiter().isEmpty()) {
                delimiter = LocalDelimiter.getDelimiter();
            }
            String joinedStr = String.join(delimiter, itemList);
            Minecraft.getInstance().keyboardHandler.setClipboard(joinedStr);
        }).pos(5, 5).size(80, 30).build());
    }
}
