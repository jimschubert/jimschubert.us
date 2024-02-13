# jimschubert-resume

> The job search sucks, your resume shouldn't.

This is a dead-simple resume template. It wraps the free Orbit theme by Xiaoying Riley into a Hugo template.

To use in your project, follow the Hugo instructions for initializing and referencing a theme.

For example:

```shell
hugo new site quickstart
cd quickstart
git init
git submodule add https://github.com/jimschubert/jimschubert-resume.git themes/jimschubert-resume
echo "theme = 'jimschubert-resume'" >> hugo.toml
cp themes/jimschubert-resume/exampleSite/data/content.yaml data/content.yaml
```

Next, edit the file at `data/content.yaml` to your liking.

## Parameters

The following parameters can be defined to customize the theme:

| Param               | Default   |
|---------------------|-----------|
| themeColor          | `#4B6A78` |
| textColor           | `#3F4650` |
| textColorSecondary  | `#545E6C` |
| textGrey            | `#97AAC3` |
| divider             | `#e8e8e8` |
| lighterGrey         | `#ccc`    |
| darkerGrey          | `#666`    |
| smokyWhite          | `#f5f5f5` |
| sidebarPadding      | `20px`    |
| educationMain       | `false`   |

For example, if using YAML for your hugo config:

```yaml
params:
  educationMain: true
  themeColor: #ececec
```
