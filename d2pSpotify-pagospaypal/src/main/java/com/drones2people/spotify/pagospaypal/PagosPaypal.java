package com.drones2people.spotify.pagospaypal;

import org.apache.commons.lang3.SystemUtils;

import java.util.Scanner;


/**
 * @author ivangarrera
 *
 */
public class PagosPaypal {

    public PagosPaypal() {}

    public boolean pagar() {
        if (SystemUtils.IS_OS_LINUX) {
            try {
                String[] commandUnix = {"wget"," https://www.paypal.me"};

                ProcessBuilder builder = new ProcessBuilder(commandUnix);
                builder.redirectErrorStream(true);
                Process process = builder.start();
                Scanner scanner = new Scanner(process.getInputStream());

                while (scanner.hasNext()) {
                    System.out.println(scanner.nextLine());
                }
                process.waitFor();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (SystemUtils.IS_OS_WINDOWS) {
            try {
                String [] commandWindows = {"Invoke-WebRequest", "-URI", "https://www.paypal.me"};

                ProcessBuilder builder = new ProcessBuilder(commandWindows);
                builder.redirectErrorStream(true);
                Process process = builder.start();
                Scanner scanner = new Scanner(process.getInputStream());

                while (scanner.hasNext()) {
                    System.out.println(scanner.nextLine());
                }
                process.waitFor();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
}
