<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Verification email</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>

    <body>
        <h3>Hello, ${name}</h3><p>
            You registered an account on FitnessApp, before being able to use your account<p>
            you need to verify that this is your email address by clicking here:
            <a href="http://localhost:80/api/v1/users/verification?code=${code}&mail=${mail}">link</a>.

        <h4>Sincerely, FitnessApp developers.</h4>
    </body>

</html>