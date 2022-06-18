import React, { useState } from 'react';
import { StyleSheet, Text, View, TextInput, Button } from 'react-native';

const LoginScreen = ({navigation}) => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [processing, setProcessing] = useState(false);

    const submit = () => {

    }

    return(
        <View style={styles.container}>
            <Text style={styles.title}>Login</Text>

            <Text style={styles.label}>Username:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setUsername}
                value={username}
                placeholder="Username"
                autoCorrect={false}
                maxLength={15}
            />
            <View style={styles.separator} />

            <Text style={styles.label}>Password:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setPassword}
                value={password}
                placeholder="Password"
                autoCorrect={false}
                maxLength={15}
                secureTextEntry={true}
            />
            <View style={styles.separator} />
            <View style={styles.separator} />

            <Button title='Submit' onPress={submit} disabled={processing} />
        </View>
    )
}

const styles = StyleSheet.create({
    title: {
        fontSize: 24,
        marginBottom: 18,
        textAlign: "center"
    },
    container: {
        flex: 1,
        padding: 16,
        paddingTop: 48,
        backgroundColor: '#fff',
        justifyContent: 'start',
    },
    input: {
        fontSize: 18,
        borderColor: "#555",
        borderRadius: 4,
        borderWidth: 1,
        padding: 4
    },
    label: {
        fontSize: 18
    },
    separator: {
        marginVertical: 6
    }
});

export default LoginScreen;