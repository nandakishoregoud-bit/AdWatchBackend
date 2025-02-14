const functions = require("firebase-functions");
const admin = require("firebase-admin");
const crypto = require("crypto");

admin.initializeApp();

// Load AdMob Secret Key securely from Firebase Config
const ADMOB_SECRET = functions.config().admob.secret;

exports.verifyAdReward = functions.https.onRequest(async (req, res) => {
    const { user_id, reward_amount, transaction_id, signature } = req.query;

    if (!user_id || !reward_amount || !transaction_id || !signature) {
        return res.status(400).send("Missing parameters");
    }

    // Verify AdMob signature
    const expectedSignature = crypto.createHmac("sha256", ADMOB_SECRET)
        .update(transaction_id)
        .digest("hex");

    if (expectedSignature !== signature) {
        return res.status(403).send("Invalid signature");
    }

    try {
        const rewardRef = admin.firestore().collection("rewards").doc(transaction_id);
        const doc = await rewardRef.get();

        if (doc.exists) {
            return res.status(409).send("Reward already processed");
        }

        await admin.firestore().collection("users").doc(user_id).update({
            rewards: admin.firestore.FieldValue.increment(parseInt(reward_amount))
        });

        await rewardRef.set({
            user_id,
            reward_amount,
            transaction_id,
            timestamp: admin.firestore.FieldValue.serverTimestamp()
        });

        res.status(200).send("Reward granted successfully");
    } catch (error) {
        console.error("Error processing reward:", error);
        res.status(500).send("Internal Server Error");
    }
});
