# 📝 TextEditor

A simple **Notepad-like text editor** built in Java using **Swing**.

It supports basic text editing features along with some advanced utilities like **Find**, **Replace**, **Word Wrap**, **Font selection**, and **Printing**.

---

## ✨ Features

### 📂 File Operations

* 🆕 **New**
* 📂 **Open** (`.txt` files)
* 💾 **Save** (overwrite existing file)
* 💾 **Save As** (save as a new file)
* 🖨️ **Print**
* 🗒️ **Page Setup**
* ❌ **Exit**

### ✏️ Edit Operations

* ✂️ **Cut**
* 📋 **Copy**
* 📌 **Paste**
* 🗑️ **Delete**
* 🔹 **Select All**
* ⏰ **Time Stamp**
* 🔍 **Find & Replace**
* 📍 **Go To Line**

### 🎨 Format

* 🔄 **Word Wrap** (toggle)
* 🔠 **Font chooser** (change font family, size, and style)

### ℹ️ Help

* 💡 **About dialog**

---

## 🖥️ Requirements

* **Java 8 or higher**
* No external libraries required; built entirely with **Java Swing** and **AWT**.

---

## 🚀 How to Run

1. **Compile the program**:

```bash
javac TextEditor.java
```

2. **Run the program**:

```bash
java TextEditor
```

> The editor window will open, allowing you to create, edit, and save text files.

---

## 📝 Notes

* 🔄 **Word Wrap** is supported using `JTextArea.setLineWrap(true)`.
* 🔠 **Font selection** is implemented via a custom `JFontChooser` class.
* 🖨️ **Printing** uses the default Java printing API.
* All features are similar to a basic Notepad editor for desktop.

---

## 📂 File Structure

```
.
│
├── TextEditor.java        # Main editor code with GUI
├── JFontChooser.java      # Helper class for font selection
└── README.md              # Project documentation
```

---

## 👤 Author

Developed by Souvik Biswas✨

---
