## MyGit Methods

`Index` class has a few main methods[^1]

##### Primary
- `init()`: initialize `index` file inside `objects` directory
- `add(String)`: add a file
- `remove(String)`: remove a file

##### Helpers
- `getMapFromFile(File)`: creates a `HashMap<String, String>` from `index` file
- `setFile()`: set our hashmap to the `index` file

### Example use case
```java
Index i = new Index();
i.init();
i.add("test.txt");
i.remove("test.txt");
```

## About
Made by Aariz

[^1]: all methods are void
