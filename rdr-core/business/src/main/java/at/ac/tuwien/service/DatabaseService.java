package at.ac.tuwien.service;

import java.util.List;

import org.apache.commons.lang.SystemUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

import at.ac.tuwien.api.dto.CreateDatabaseContainerDto;
import at.ac.tuwien.mapper.DatabaseSqlMapper;

@Service
public class DatabaseService {

	private DatabaseSqlMapper mapper;

	private final String localDockerHost = SystemUtils.IS_OS_WINDOWS ? "tcp://localhost:2375"
			: "unix:///var/run/docker.sock";

	private final DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
			.withDockerHost(localDockerHost).build();


	public void createDatabaseContainer(CreateDatabaseContainerDto dto) {

		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		int availableTcpPort = SocketUtils.findAvailableTcpPort(8180, 8500);
		HostConfig hostConfig = HostConfig.newHostConfig()
				.withPortBindings(PortBinding.parse(String.valueOf(availableTcpPort) + ":5432"));

		CreateContainerResponse container = dockerClient.createContainerCmd("rdr-postgres:1.0")
				.withName(dto.getContainerName())
				.withEnv("POSTGRES_DB=" + dto.getDbName(), "POSTGRES_PASSWORD=mysecretpassword")
				.withHostConfig(hostConfig).exec();

		dockerClient.startContainerCmd(container.getId()).exec();

	}

	public List<Container> getDatabaseContainers() {
		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		List<Container> containers = dockerClient.listContainersCmd().exec();

		return containers;
	}

}
