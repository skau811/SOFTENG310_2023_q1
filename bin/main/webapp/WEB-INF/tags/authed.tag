<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">

    <title>University of Auckland: Class enrollment system</title>
</head>
<body>
<div class="container">

    <!-- Navbar Start -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
        <a class="navbar-brand" href="/">
            <img src="https://cpb-ap-se2.wpmucdn.com/blogs.auckland.ac.nz/dist/d/79/files/2015/10/uoa-v-reverse1.png"
                 alt="University of Auckland"
                 style="max-height: 100px">
        </a>
        <button class="navbar-toggler"
                type="button"
                ata-toggle="collapse" 
                data-target="#app-navbar"
                aria-controls="app-navbar"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="app-navbar">

            <%--   ADD NEW PAGES HERE   --%>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/AcademicProfile">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link view-classes" href="/my-classes">View Classes</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link log-out" id = "logOutLink" href="/">Log Out</a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- Navbar End   -->

    <jsp:doBody />
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>

<script type="application/javascript">
    function applyForClass(event){
        console.log(event.target);
        var className = event.target.id.split("EnrolBtn")[0];
        var xhr = new XMLHttpRequest();
        xhr.onload = function() {
            if (this.status == 200) {
                console.log("Status 200");
                console.log(xhr.responseText);
                $('#' + event.target.id).text("Enrolled");
            }
        }
        xhr.open("POST","/addClass?class=" + className, true);
        xhr.send();
    }

    function applyForConcession(event){
        console.log(event);
        document.getElementById(event.target.id).text = ("Request sent");
    }
</script>
</body>
</html>