# vim.md
Monday, April 15th 2019, 20:47

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [vim.md](#vimmd)
* [vim](#vim)
* [neovim](#neovim)
* [basic config](#basic-config)
	* [修改插入模式的光标为竖线](#修改插入模式的光标为竖线)
* [plugins](#plugins)
	* [plugin manager](#plugin-manager)
	* [space vim](#space-vim)

<!-- /code_chunk_output -->

# vim

install [plugin manager](#plugin-manager) first, config the path

[my own configured vimrc](assets/vimrc)

# neovim

```shell
sudo add-apt-repository ppa:neovim-ppa/stable
sudo apt update
sudo apt install neovim
```

# basic config

refer :point_right: [阮一峰的博客](https://www.ruanyifeng.com/blog/2018/09/vimrc.html)

## 修改插入模式的光标为竖线

refer :point_right: [改为竖线](https://vim.fandom.com/wiki/Change_cursor_shape_in_different_modes)

# plugins

refer :point_right: [plugins recommended](https://jdhao.github.io/2018/09/05/centos_nvim_install_use_guide/)

## plugin manager

```shell
curl -fLo ~/.config/nvim/autoload/plug.vim --create-dirs https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim
```

安装tagbar的依赖

```shell
sudo apt install ctags
```

## space vim

refer :point_right: [SpaceVim](https://spacevim.org/)
