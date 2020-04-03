package at.ac.tuwien.service;

import java.util.List;

import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

import at.ac.tuwien.dto.CreateDatabaseDto;
import at.ac.tuwien.mapper.DatabaseSqlMapper;
import at.ac.tuwien.persistence.impl.DatabaseDaoImpl;

@Service
public class DatabaseService {
	@Autowired
	private DatabaseDaoImpl dao;

	private DatabaseSqlMapper mapper;

	private final String localDockerHost = SystemUtils.IS_OS_WINDOWS ? "tcp://localhost:2375"
			: "unix:///var/run/docker.sock";

	private final DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
			.withDockerHost(localDockerHost).build();

	public void create(CreateDatabaseDto dto) {
		createDatabaseContainer(dto.getDbName(), dto.getContainerName());
//		dao.executeQuery(mapper.fromCreateDto(databaseDto));
	}

	public List<String> getDatabases() {
		return dao.getDatabases();
	}

	public void createDatabaseContainer(String dbName, String containerName) {

		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		int availableTcpPort = SocketUtils.findAvailableTcpPort(8180, 8500);
		HostConfig hostConfig = HostConfig.newHostConfig()
				.withPortBindings(PortBinding.parse(String.valueOf(availableTcpPort) + ":5432"));

		CreateContainerResponse container = dockerClient.createContainerCmd("postgres").withName(containerName)
				.withEnv("POSTGRES_DB=" + dbName, "POSTGRES_PASSWORD=mysecretpassword").withHostConfig(hostConfig)
				.exec();

		dockerClient.startContainerCmd(container.getId()).exec();

	}

	public void listDatabaseContainers() {
		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		List<Container> containers = dockerClient.listContainersCmd().exec();

		for (Container c : containers) {
			System.out.println(c.getNames()[0]);
		}

	}

}
