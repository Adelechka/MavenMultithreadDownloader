package ru.itis.homework.app;

import com.beust.jcommander.JCommander;
import com.itis.Downloader;
import com.itis.ThreadPool;


import java.util.ArrayList;

public class Program {
    public static void main(String argv[]) {

        Args args = new Args();
        JCommander.newBuilder().addObject(args).build().parse(argv);
        ArrayList<String> arrayList= (ArrayList<String>) args.files;
        Downloader downloader = new Downloader();

        class Task implements Runnable {

            @Override
            public void run() {
                synchronized (arrayList) {
                    if (!arrayList.isEmpty()) {
                        String task = arrayList.remove(0);
                        System.out.println(Thread.currentThread().getName() + " is downloading: " + task);
                        downloader.download(task, args.folder);
                    }
                }
            }
        }

        if (args.mode.equals("one-thread")) {
            for (String url : args.files) {
                System.out.println("downloading: " + url);
                downloader.download(url, args.folder);
            }
        }

        else if (args.mode.equals("multi-thread")) {
            ThreadPool threadPool = new ThreadPool(args.count);
            while (!arrayList.isEmpty()){
                Task task = new Task();
                threadPool.submit(task);
            }
        }
    }
}