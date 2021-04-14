let books = [];
let user = null

const validateISBN = isbn => {
    const arr = isbn.split('-');
    const str = arr.filter((_, i) => i < 3).join('');
    const numbers = str.split('').map(item => Number.parseInt(item));
    let result = 0;
    for (let i = 0; i < numbers.length; i++) {
        result += numbers[i] * i;
    }
    result /= 11;
    return Math.floor(result) == arr[3];
}

const validateUser = (username, password) => {
    const correctPassword = password !== ""
        && password.length > 8
        && password.length < 20;;
    const correctUsername = username !== "" && username.match("^[A-Za-z0-9]+$");
    return correctPassword && correctUsername;
}

const callGet = async (url) => {
    const serverData = await fetch(url, {
        mode: "no-cors"
    });
    const book = await serverData.json();
    return book;
}
const callPost = async (url, data = {}) => {
    const serverData = await fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(data)
    });
    const response = await serverData.json();
    return response;
}


async function repaintRules() {
    let user = JSON.parse(localStorage.getItem("user"));
    if (user !== null) {
        $("#loginPage").css("display", "none");
        $("#favourite").css("display", "inherit");
        $("#unlog").css("display", "inherit");
    } else {
        $("#loginPage").css("display", "inherit");
        $("#favourite").css("display", "none");
        $("#unlog").css("display", "none");
    }
    const res = await callGet('/all');
    books = res;
    repaintAll(books);
};

$("#unlog").on("click", () => {
    localStorage.removeItem("user");
    repaintRules();
})
$("#button-search").on("click", async () => {
    const data = $("#param").val().trim();
    const res = await callGet(`/getOneBook/${data}`);
    repaintAll([res]);
});
$("#get-all").on("click", async () => {
    const data = await callGet('/all');
    repaintAll(data);
});

$("#add-book").on("click", async (e) => {
    e.preventDefault();
    const name = $("#exampleFormControlInput1").val().trim();
    const author = $("#exampleFormControlInput3").val().trim();
    const isbn = $("#exampleFormControlInput2").val().trim();
    if (validateISBN(isbn) && name !== "" && author !== "") {
        const book = {title: name, author: author, isbn: isbn};
        try {
            const res = await callPost('/addBook', book);
            books.push(res);
            alert("Ok")
        } catch (e) {
            alert("Something went wrong");
        }
        repaint();
    } else {
        alert("ISBN incorrect (example - X-XX-XXXXXX-X) or not all fields are filled")
    }
})

$("#add-user").on("click", async (e) => {
    e.preventDefault();
    const username = $("#username").val();
    const password = $("#password").val();
    if (validateUser(username, password)) {
        try {
            const CommonUser = {username: username, password: password};
            const res = await callPost('/registration', CommonUser);
            localStorage.setItem("user", JSON.stringify(res));
            alert("Ok");
        } catch (e) {
            alert("User with such credentials already exists");
        } finally {
            repaintRules();
        }
    } else {
        alert("Password must have length more than 8 and less than 20 and username " +
            "can contains only letters and numbers");
    }
})

$("#login").on("click", async (e) => {
    e.preventDefault();
    const username = $("#username").val();
    const password = $("#password").val();
    if (validateUser(username, password)) {
        try {
            const CommonUser = {username: username, password: password};
            const res = await callPost('/login', CommonUser);
            localStorage.setItem("user", JSON.stringify(res));
            alert("Ok");
        } catch (e) {
            alert("Something went wrong");
        } finally {
            repaintRules();
        }
    } else {
        alert("Password must have length more than 8 and less than 20");
    }
})

const repaint = () => {
    appendLastBook();
}
const repaintAll = (books) => {
    const root = $("#root");
    root.html("");
    if (books !== undefined) booksAppend(books);

}

const booksAppend = (book) => {
    const root = $("#root");
    book.forEach(item => {
        root.append(getBookHTML(item));
    })
}

const appendLastBook = () => {
    const root = $("#root");
    root.append(getBookHTML(books[books.length - 1]))
}

const getBookHTML = (book) => {
    return "<div " +
        "             class=\"card m-3\"\n" +
        "             style=\"width: 18rem;\">\n" +
        "            <div class=\"card-body\">\n" +
        "                <h5 " +
        "                  class=\"card-title\">" + "Title: " + book.title + "</h5>\n" +
        "                <h5 " +
        "                    class=\"card-subtitle mb-2 text-muted\">" + "Author: " + book.author + "</h5>\n" +
        "                <h6 " +
        "                    class=\"card-subtitle mb-2 text-muted\">" + "ISBN: " + book.isbn + "</h6>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>"
}

repaintRules();