# Bootstrap.md
Thursday, February 21st 2019, 20:01

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [Bootstrap.md](#bootstrapmd)
* [container](#container)
	* [fluid](#fluid)
	* [fixed 固定](#fixed-固定)
* [Grid system](#grid-system)

<!-- /code_chunk_output -->

# container

based on Bootstrap v3.3.7

## fluid

容器的width为auto, 两边各加15px的padding

## fixed 固定

容器的width随分辨率改变而改变

分辨率阈值:

1. width >= 1200: 容器的width为1170 左右padding各15

2. 1200 > width >= 992: 容器的width为970 左右padding各15

3. 992 > width >= 768: 容器的width为750 左右padding各15

4. 768 > width: 容器的width为auto 左右padding各15

# Grid system

container-fixed和container-fluid的公共样式

```less
// @grid-gutter-width: 30px
.container-fixed(@gutter: @grid-gutter-width) {
  margin-right: auto;
  margin-left: auto;
  padding-left:  floor((@gutter / 2));
  padding-right: ceil((@gutter / 2));
  &:extend(.clearfix all);
}
```

container-fixed固定样式

```less
// @screen-sm-min = @screen-sm-min = @screen-tablet = 768px
// @screen-md-min = @screen-md = 992px
// @screen-lg-min = @screen-lg = 1200px

// @container-sm = @container-tablet = 720px + @grid-gutter-width(30px)
// @container-md = @container-desktop = 940px + @grid-gutter-width
// @container-lg = @container-large-desktop = 1140px + @grid-gutter-width
.container {
  .container-fixed();

  @media (min-width: @screen-sm-min) {
    width: @container-sm;
  }
  @media (min-width: @screen-md-min) {
    width: @container-md;
  }
  @media (min-width: @screen-lg-min) {
    width: @container-lg;
  }
}
```
