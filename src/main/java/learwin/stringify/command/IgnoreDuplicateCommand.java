package learwin.stringify.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import learwin.stringify.client.LocalDelimiter;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;

public class IgnoreDuplicateCommand extends BaseCommand{

    public IgnoreDuplicateCommand(String name, int permLevel) {
        super(name, permLevel);
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> setExecution() {
        return builder.then(Commands.argument(name, StringArgumentType.word())
                        .executes((source -> execute(source.getSource()))));
    }

    private int execute(CommandSourceStack source) {
        LocalDelimiter.setIgnoreDuplicates(!LocalDelimiter.shouldIgnoreDuplicates());
        Minecraft.getInstance().player.sendSystemMessage(Component.translatable("stringify.toggledduplicate.message").append(": " + !LocalDelimiter.shouldIgnoreDuplicates()));
        return Command.SINGLE_SUCCESS;
    }
}
