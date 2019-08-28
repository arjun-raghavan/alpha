import React, { useState } from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import { Link } from "react-router-dom";
import Grid from "@material-ui/core/Grid";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";

const useStyles = makeStyles(theme => ({
  "@global": {
    body: {
      backgroundColor: theme.palette.common.white
    }
  },
  root: {
    height: "100%"
  },
  paper: {
    margin: theme.spacing(8, 4),
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(3)
  },
  margin: {
    margin: theme.spacing(1, 0)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  }
}));

const signUpRequest = async (username, password, name, email, history) => {
  try {
    const res = await axios.post("http://localhost:8080/api/signup", {
      username,
      password,
      name,
      email
    });
    if (res.status === 201) {
      alert("registered successfully");
      history.push("/login");
    }
  } catch (error) {
    console.error(error);
  }
};

export default function SignUp({ history }) {
  const classes = useStyles();

  const [firstName, setFirstName] = useState("");
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  function updateFirstName(event) {
    setFirstName(event.target.value);
  }

  function updateUserName(event) {
    setUserName(event.target.value);
  }

  function updatePassword(event) {
    setPassword(event.target.value);
  }

  function updateEmail(event) {
    setEmail(event.target.value);
  }

  function handleResponse(event) {
    event.preventDefault();
    signUpRequest(userName, password, firstName, email, history);
  }

  return (
    <div className={classes.paper}>
      <Avatar className={classes.avatar}>
        <LockOutlinedIcon />
      </Avatar>
      <Typography component="h1" variant="h5">
        Sign up
      </Typography>
      <form className={classes.form} onSubmit={handleResponse}>
        <Grid spacing={2}>
          <TextField
            variant="outlined"
            required
            fullWidth
            id="firstName"
            label="First Name"
            name="firstName"
            autoFocus
            autoComplete="fname"
            value={firstName}
            onChange={updateFirstName}
            className={classes.margin}
          />
          <TextField
            variant="outlined"
            required
            fullWidth
            id="userName"
            label="User Name"
            name="userName"
            autoComplete="Uname"
            value={userName}
            onChange={updateUserName}
            className={classes.margin}
          />
          <TextField
            variant="outlined"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoComplete="email"
            value={email}
            onChange={updateEmail}
            className={classes.margin}
          />
          <TextField
            variant="outlined"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            value={password}
            onChange={updatePassword}
            className={classes.margin}
          />
          <FormControlLabel
            control={<Checkbox value="allowExtraEmails" color="primary" />}
            label="I want to receive inspiration, marketing promotions and updates via email."
          />
        </Grid>
        <Button
          fullWidth
          type="submit"
          variant="contained"
          color="primary"
          className={classes.submit}
        >
          Sign Up
        </Button>
      </form>
      <Grid container>
        <Grid item>
          <Link component={SignUp} to="/login">
            Already have an Account ? Sign In
          </Link>
        </Grid>
      </Grid>
    </div>
  );
}
