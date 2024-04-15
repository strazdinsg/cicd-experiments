# CI/CD Experiments

Experiments with CI/CD pipelines (GitHub actions, etc.).

## Projects

1. [00 Hello, GitHub Actions](/.github/workflows/00-hello-github-actions.yml) - a proof of concept.
2. [01 No README](.github/workflows/01-no-readme.yml) - don't run the workflow when only README file
   changes.
3. [02 Manually triggered]() - a workflow that is triggered on every commit in the main 
   branch, but can also be triggered manually.
4. [03 Use custom actions] - a custom action defined and used in the workflow. 

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
- 


