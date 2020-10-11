package ru.itis.homework.app;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.itis.FileConverter;

import java.util.List;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = {"--mode"})
    public String mode;

    @Parameter(names = {"--count"})
    public int count;

    @Parameter(names = {"--files"}, listConverter = FileConverter.class)
    public List<String> files;

    @Parameter(names = {"--folder"})
    public String folder;
}
