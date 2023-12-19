<nav class="navbar navbar-expand-lg fixed-top" style="background-color: #481f1f">
    <#--    #fff533-->
    <div class="container-fluid">
        <a class="navbar-brand" href="http://localhost:8080/"><span class="material-symbols-outlined inline"
                                                                    style="padding-top: 3px">home</span>Nani?Libria</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse " id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="http://localhost:8080/chat"><span
                                class="material-symbols-outlined">groups2</span>Chat</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="http://localhost:8080/faq"><span
                                class="material-symbols-outlined">assistant_direction</span>FAQ</a>
                </li>
                <li>
                    <a class="nav-link active themeToggle" href="#"><span
                                class="material-symbols-outlined">bedtime</span></a>
                </li>

            </ul>
            <form class="d-flex" role="search" method="get" action="http://localhost:8080/search">
                <span class="material-symbols-outlined" style="padding-top: 7px">search</span>
                <input class="form-control me-2" type="search" name="title" id="exampleDataList"
                       placeholder="Type to search...">
                <button class="btn btn-outline-dark" type="submit">Search</button>
            </form>
            <p class="badge bg-warning text-dark text-wrap fst-italic fs-6 mx-auto" style="height: 3%">Manga description site</p>
            <#if !user??>
                <ul class="nav justify-content-end">
                    <li class="btn-group mx-auto" role="group" aria-label="Basic outlined example">
                        <a class="btn btn-warning text-dark" href="http://localhost:8080/login" role="button">Sign In</a>
                        <a class="btn btn-dark" href="http://localhost:8080/register" role="button">Sign Up</a>
                    </li>
                </ul>
            </#if>
            <#if user??>
                <div class="dropdown">
                    <button class="btn btn-warning text-dark dropdown-toggle" type="button" data-bs-toggle="dropdown"
                            id="dropdownMenuButton" aria-haspopup="true" aria-expanded="false">
                        ${user.username}
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
                            <path fill-rule="evenodd"
                                  d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"></path>
                        </svg>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href='/profile'>Profile</a></li>
                        <li><a class="dropdown-item" href='/edit'>Edit</a></li>
                        <#if user.role == 'redactor'>
                            <li><a class="dropdown-item" href='/redactor'>Redact</a></li>
                        </#if>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li>
                            <form action='/' method="post">
                                <button class="dropdown-item btn-warning text-dark" name="logout" value="out">Log out</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </#if>
        </div>
    </div>
</nav>