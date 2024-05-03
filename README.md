# CI/CD Experiments

Experiments with CI/CD pipelines (GitHub actions, etc.).

## Projects

1. [Hello, GitHub Actions](/.github/workflows/00-hello-github-actions.yml) - a proof of concept.
2. [No README](.github/workflows/01-no-readme.yml) - don't run the workflow when only README file
   changes.
3. [Manually triggered]() - a workflow that is triggered on every commit in the main branch, but can
   also be triggered manually.
4. [Use custom actions](.github/workflows/03-reuse-local-action.yml) - a custom action defined and
   used in the workflow.
5. [Share file](.github/workflows/04-share-file.yml) - Share a file between two jobs using GitHub
   artifact storage.
6. [Maven Tests](.github/workflows/05-maven-tests.yml) - Run unit tests for a Maven Java app.
7. [CheckStyle and SonarLint linters](.github/workflows/06-linters.yml) - Run CheckStyle and
   SonarLint linters for the code structure.
8. [Java Azure App Service deploy](.github/workflows/07-java-azure-app-service.yml) - Build a Java
   Spring Boot with Maven, deploy it on
   [Azure App Service](https://azure.microsoft.com/en-us/products/app-service).
9. [Java deploy with systemctl](.github/workflows/08-java-on-custom-server.yml) - Build a Java
   Spring Boot app with Maven, deploy it on a custom Ubuntu Linux server as a system service
   with `systemctl`.
10. [Publish to Docker](.github/workflows/09-publish-to-docker.yml) - Build a Docker image, publish
    it to DockerHub.
11. [Java deploy with Docker](.github/workflows/10-java-with-docker.yml) - Build a Java Spring Boot
    app with Maven, deploy it on a custom Ubuntu Linux server using Docker and DockerHub.
12. [Java deploy with Supervisor](.github/workflows/11-java-with-supervisor.yml) - Build a Java
    Spring Boot app with Maven, deploy it on a custom Ubuntu Linux server using
    [Supervisor](http://supervisord.org/).
13. [Flutter app](.github/workflows/101-flutter-app.yml) - Package a Flutter app to an Android APK.
14. [Flutter multi-platform](.github/workflows/102-flutter-multi-platform.yml) - Package a Flutter
    app to Web, Android and iOS, using multi-platform build, with parallel runners.

## Lessons learned

- The `.github/workflows` directory must be repository-wide, placed in the root of the Git repo.
  I.e., you can't have multiple `.github` repositories, in sub-folders. You can, however, have
  subfolders inside the `.github/workflows` folder.
- Step names can be written as strings using "names in double quotes", or simply the name without
  any quotes:

```yaml
steps:
  - name: Clone repository
    uses: actions/checkout@v4
#   ...
```

- When specifying which paths to include in a `push` event, use the `**` syntax, for example:
  `paths: [ 'hello-java/**' ]`.

- For SonarLint you need to set up a project on SonarCloud,
  see [instructions in the GitHub action README](https://github.com/SonarSource/sonarcloud-github-action?tab=readme-ov-file#requirements).

- Deployment to Azure AppService is easier if one allows Azure to create the workflow file (when
  setting up CI/CD in the Azure portal). Alternatively, use the `publish-profile` approach, as
  described
  [here](https://learn.microsoft.com/en-us/azure/app-service/deploy-github-actions#set-up-a-github-actions-workflow-manually)
  In short: Go to the AppService in the Azure Portal > Overview > Download Publish Profile, save the
  whole content of that file as a GitHub Secret in your repo named
  `AZURE_WEBAPP_PUBLISH_PROFILE`, see the
  [workflow yaml file](.github/workflows/07-java-azure-app-service.yml#L50).

## Necessary Secrets

If you clone this repository and want to try the example actions, you will need to set up tne
following [secrets for the GitHub repository](https://docs.github.com/en/actions/security-guides/using-secrets-in-github-actions):

- `SONAR_TOKEN` - [token for SonarCloud](https://sonarcloud.io/account/security)
- `AZURE_WEBAPP_PUBLISH_PROFILE` - access information
  for [Azure AppService](https://learn.microsoft.com/en-us/azure/app-service/deploy-github-actions#set-up-a-github-actions-workflow-manually).
- SSH deployment related secrets for [Example 08](.github/workflows/08-java-on-custom-server.yml):
    - `SSH_HOST` - hostname or IP address of the server
    - `SSH_USER` - username of the deployment user
    - `SSH_PRIVATE_KEY` - private key for the deployment user
    - `SSH_TARGET_PATH` - target directory where to put the JAR file

## Restarting your app with `systemd`

In
the [example where we deploy an app with `systemctl`](.github/workflows/08-java-on-custom-server.yml),
you need to first register your app as a service for the `systemd`. To do that:
1. Describe the service in a config file `/etc/systemd/system/my-java-app.service`.
2. Reload the `systemd` and start the service.
3. Enable the service on reboot.

### Service configuration file
Save the following content in `/etc/systemd/system/my-java-app.service`:
```ini
[Unit]
Description=My Custom Java Server App
After=network.target

[Service]
ExecStart=java -jar /home/deploy/hello.jar
Restart=always
User=root
Group=root

[Install]
WantedBy=multi-user.target
```

### Reloading `systemd`
```bash
sudo systemctl daemon-reload
sudo systemctl start my-java-app.service
```

### Enabling the service on reboot
```bash
sudo systemctl enable my-java-app.service
```

### Check whether the service is live
```bash
sudo systemctl status my-java-app.service
```

### Restart the service
```bash
sudo systemctl restart my-java-app.service
```