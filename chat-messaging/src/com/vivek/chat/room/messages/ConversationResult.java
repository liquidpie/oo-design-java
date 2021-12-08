package com.vivek.chat.room.messages;

import java.util.List;

/**
 * This API would be called if I need to show a page of conversations/threads ( Think of the view you see when you open the Whatsapp / Messenger app ).
 * At a time, only a certain number of conversations will be in the viewport ( let's say 20 ). Let's call it a page of conversations. For a user, we would only want to fetch a page of conversations at a time.
 * Gotcha: Would the page size remain constant across different situations?
 * Probably not. The page size would be different across clients based on screen size and resolution. For example, a mobile’s page size might be lower than that of a web browser’s.
 *
 * Delta fetch: In most cases, our API calls will be made by users who are active on the site. As such, they already have a view of conversations till a certain timestamp and are only looking for updates after the timestamp ( which would typically be 0-2 more conversations ). For clients which are data sensitive (like mobile), fetching the whole page every time even when I have all of the conversations can be draining. So, we need to support a way of fetching only the updates when the lastFetchedTimestamp is closer to currentTimestamp.
 */
public class ConversationResult {

    List<Conversation> conversations;
    boolean isDeltaUpdate;

}
