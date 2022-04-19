package me.srgantmoomoo.postman.framework.command;

import java.util.Arrays;
import java.util.List;

public abstract class Command {
    private final String name, description, syntax;

    public List<String> aliases;

    public Command(String name, String description, String syntax, String... aliases) {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
        this.aliases = Arrays.asList(aliases);
    }

    public abstract void onCommand(String[] args, String command);

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public String getSyntax() {
        return syntax;
    }

    public List<String> getAliases() {
        return this.aliases;
    }
}
