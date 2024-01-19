package learwin.stringify.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class BaseCommand {

    protected LiteralArgumentBuilder<CommandSourceStack> builder;
    protected String name;

    public BaseCommand(String name, int permLevel) {
        this.builder = Commands.literal("stringify").then(Commands.literal(name)).requires(source -> source.hasPermission(permLevel));
        this.name =name;
    }

    public LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return builder;
    }

    public LiteralArgumentBuilder<CommandSourceStack> setExecution() {
        return null;
    }
}
