package app;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;

@Startup
@Singleton
public class StartApp {

	private static DatabaseClient client;
	private static String trenutniPort;

	public StartApp() {

	}

	@PostConstruct
	public void init() {

		System.out.println("POKRENUO BANKU");
//		uzmiPort();
		otvoriKonekciju();
			
	}

/*	public void uzmiPort() {
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		String portPart = "";
		String offset = "";

		try {

			String sCurrentLine;

			br1 = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br1.readLine()) != null) {

				if (sCurrentLine.contains("jboss.http.port")) {

					String[] foundPort = sCurrentLine.split(":");
					portPart = (foundPort[1].split("}"))[0];

					break;

				}

			}

			br2 = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br2.readLine()) != null) {

				if (sCurrentLine.contains("jboss.socket.binding.port-offset")) {

					String[] foundOffset = sCurrentLine.split(":");
					offset = (foundOffset[1].split("}"))[0];

					break;

				}

			}

			int portPartInt = Integer.parseInt(portPart);
			int offsetInt = Integer.parseInt(offset);
			int portInt = portPartInt + offsetInt;
			trenutniPort = String.valueOf(portInt);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br1 != null)
					br1.close();

				if (br2 != null)
					br2.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	} */

	@PreDestroy
	public void exit() {
		zatvoriKonekciju();
	}

	public static void otvoriKonekciju() {
		client = DatabaseClientFactory.newClient("localhost", 8003, "admin", "admin", Authentication.DIGEST);

	}

	public static void zatvoriKonekciju() {
		client.release();
	}

	public static DatabaseClient getClient() {
		return client;
	}

	public static void setClient(DatabaseClient client) {
		StartApp.client = client;
	}

	public static String getTrenutniPort() {
		return trenutniPort;
	}

	public static void setTrenutniPort(String trenutniPort) {
		StartApp.trenutniPort = trenutniPort;
	}
}