# CI/CD Experiments

Experiments with CI/CD pipelines (GitHub actions, etc.).

## Projects

1. [00 Hello, GitHub Actions](/.github/workflows/00-hello-github-actions.yml) - a proof of concept.
2. [01 No README](.github/workflows/01-no-readme.yml) - don't run the workflow when only README file
   changes.
3. [02 Manually triggered]() - a workflow that is triggered on every commit in the main
   branch, but can also be triggered manually.
4. [03 Use custom actions](.github/workflows/03-reuse-local-action.yml) - a custom action defined
   and used in the workflow.
5. [04 Share file](.github/workflows/04-share-file.yml) - Share a file between two jobs using GitHub
   artifact storage.
6. [05 Maven Tests](.github/workflows/05-maven-tests.yml) - Run unit tests for a Maven Java app.
7. [06 CheckStyle and SonarLint linters](.github/workflows/06-linters.yml) - Run CheckStyle and
   SonarLint linters for the code structure.
8. [10 Java Azure App Service deploy](.github/workflows/10-java-azure-app-service.yml) - Build a
   Java Spring Boot with Maven, deploy it on
   [Azure App Service](https://azure.microsoft.com/en-us/products/app-service).

## Lessons learned

- The `.github/workflows` directory must be repository-wide, placed in the root of the Git repo.
  I.e., you can't have multiple `.github` repositories, in sub-folders. You can, however, have
  subfolders inside the `.github/workflows` folder.
- Step names can be written as strings using "names in double quotes", or simply the name
  without any quotes:

```yaml
steps:
  - name: Clone repository
    uses: actions/checkout@v4
#   ...
```

- When specifying which paths to include in a `push` event, use the `**` syntax, for example:
  `paths: [ 'hello-java/**' ]`.


