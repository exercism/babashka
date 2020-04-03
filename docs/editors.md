# Babashka text editor integrations

## Atom: [chlorine](https://github.com/mauricioszabo/atom-chlorine)

1. Install [atom](https://flight-manual.atom.io/getting-started/sections/installing-atom/)

2. Install the `chlorine` and `ink` packages

3. Start socket REPL:

 ```
$ bb --socket-repl 1666
Babashka socket REPL started at localhost:1666
 ```

4. Open Atom command palette (ctrl+shift+p) and execute command `Connect Clojure Socket REPL` with `localhost` `1666`

5. Edit key bindings in keymap.cson via `Edit` menu -> `Keymap...`:

```
'atom-text-editor[data-grammar="source clojure"]':
  'ctrl-; y':       'chlorine:connect-clojure-socket-repl'
  'ctrl-; e':       'chlorine:disconnect'
  'ctrl-; k':       'chlorine:clear-console'
  'ctrl-shift-enter':       'chlorine:load-file'
  'ctrl-enter':       'chlorine:evaluate-block'
  'shift-enter':       'chlorine:evaluate-top-block'
  'ctrl-; i':       'chlorine:inspect-block'
  'ctrl-; I':       'chlorine:inspect-top-block'
  'ctrl-; s':       'chlorine:evaluate-selection'
  'ctrl-; c':       'chlorine:break-evaluation'
  'ctrl-; S':       'chlorine:source-for-var'
  'ctrl-; d':       'chlorine:doc-for-var'
  'ctrl-; x':       'chlorine:run-tests-in-ns'
  'ctrl-; t':       'chlorine:run-test-for-var'
```

## Emacs: [inf-clojure](https://github.com/clojure-emacs/inf-clojure)

For Emacs, install [inf-clojure](https://github.com/clojure-emacs/inf-clojure) by adding this to your `init.el`:

```
(unless (package-installed-p 'inf-clojure)
  (package-refresh-contents)
  (package-install 'inf-clojure))

(add-hook 'clojure-mode-hook #'inf-clojure-minor-mode)
```

Start the socket REPL as above, and in emacs execute `M-x inf-clojure-connect` (C-c M-c).

Evaluate forms with C-c C-e. For a list of all available commands in inf-clojure-mode (a.k.a. the REPL) and inf-clojure-minor-mode you can either invoke C-h f RET inf-clojure-mode and C-h f RET inf-clojure-minor-mode or simply browse their menus.

## Vim: [vim-iced](https://github.com/liquidz/vim-iced)

Download the [Neovim appimage](https://github.com/neovim/neovim/releases/download/v0.4.3/nvim.appimage) and alias it to `vim`.

* Install [vim-plug](https://github.com/junegunn/vim-plug):

```
$ curl -fLo ~/.local/share/nvim/site/autoload/plug.vim --create-dirs \
    https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim
```

Create `~/.config/nvim/init.vim`:

```
call plug#begin('~/.vim/plugged')

Plug 'liuchengxu/vim-clap'
Plug 'guns/vim-sexp',    {'for': 'clojure'}
Plug 'liquidz/vim-iced', {'for': 'clojure'}

call plug#end()

" Enable vim-iced's default key mapping
" This is recommended for newbies
let g:iced_enable_default_key_mappings = v:true
```

Open Neovim and run the command `:PlugInstall`.

Add the `iced` command to the $PATH:

`$ export PATH=$PATH:~/.vim/plugged/vim-iced/bin`

* Start the socket REPL server, open source file and execute `:IcedConnectSocketRepl 1666`.

Default REPL commands (default `<Leader>` is `\`):

- `<Leader>ei` : Evaluate inner element
- `<Leader>ee` : Evaluate outer list
- `<Leader>et` : Evaluate outer top list
