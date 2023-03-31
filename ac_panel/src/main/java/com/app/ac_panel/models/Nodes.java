package com.app.ac_panel.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Nodes {
    private List<Node> nodes;

    public Node getNodeById(int id){
        return nodes.get(id);
    }
}
